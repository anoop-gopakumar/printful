package com.user.locationfinder.printful.musiclocationfinder.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.user.locationfinder.printful.musiclocationfinder.models.PlacesHolder
import com.user.locationfinder.printful.musiclocationfinder.network.getMusicBrainzService
import kotlinx.coroutines.*

class HomeViewModels : ViewModel() {

    val placesLiveData: LiveData<PlacesHolder>
        get() = _placesMutableLiveData

    private  var _placesMutableLiveData: MutableLiveData<PlacesHolder> = MutableLiveData()

    fun fetchPlaces(places: String, limit: Int, offset: Int) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            try {
                val deferred = async { getMusicBrainzService().fetchPlaces(places,offset,limit) }

                val placesHolderResponse = deferred.await()

                 if(placesHolderResponse.isSuccessful){
                     Log.d("TAG", "fetchPlaces: ${placesHolderResponse.body()}")
                     _placesMutableLiveData.postValue(placesHolderResponse.body())
                 }else{
                     Log.d("TAG", "fetchPlaces: ${placesHolderResponse.body()}")
                     _placesMutableLiveData.postValue(null)
                 }


            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                _placesMutableLiveData.value = null
            }
        }

    }
}
package com.user.locationfinder.printful.musiclocationfinder.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.user.locationfinder.printful.musiclocationfinder.viewmodels.HomeViewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeMapActivity : BaseMapActivity() {

    private val viewModels: HomeViewModels by viewModels()
    private val limit = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModels()
        viewModels.fetchPlaces("chipping",limit,0)
    }

    private fun observeViewModels() {
        viewModels.placesLiveData.observe(this,{

                if(it!=null){
                    showSnackBar("Got results ${it.count}")
                    Log.d("TAG", "observeViewModels: ${it.places?.size}")
                }else{
                    showSnackBar("No results found")
                }

        })
    }

}
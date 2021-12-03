package com.wundermobility.carrental.printful.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.locationfinder.printful.network.SocketCommunicator
import com.wundermobility.carrental.printful.models.UserInfo
import com.wundermobility.carrental.printful.models.UserUpdatedInfo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    var userUpdatedInfoLiveData: MutableLiveData<UserUpdatedInfo> = MutableLiveData()
    var userInfoLiveData: MutableLiveData<ArrayList<UserInfo>> = MutableLiveData()

    private lateinit var socketCommunicator: SocketCommunicator

    init {
        viewModelScope.launch(IO) {
            socketCommunicator = SocketCommunicator(userInfoLiveData,userUpdatedInfoLiveData)
            socketCommunicator.connectToSocket()
        }
    }

}
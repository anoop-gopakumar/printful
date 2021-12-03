package com.user.locationfinder.printful.network

import android.util.Log
import java.io.*
import java.net.Socket
import androidx.lifecycle.MutableLiveData
import com.wundermobility.carrental.printful.models.UserInfo
import com.wundermobility.carrental.printful.models.UserUpdatedInfo
import com.wundermobility.carrental.printful.utils.StringParser
import kotlinx.coroutines.*

//TODO add lifecycle observer and close all
// socket connection on destroy of lifecycle event...

class SocketCommunicator(private val userUpdatedInfoLiveData: MutableLiveData<ArrayList<UserInfo>>,
    private val userInfoLiveData: MutableLiveData<UserUpdatedInfo>
) {

    val SERVER_URL = "ios-test.printful.lv"
    val PORT_ADDRESS = 6111
    var count = 0

    suspend fun connectToSocket() {

        val client = Socket(SERVER_URL, PORT_ADDRESS)

        try {
            getMessages(client)
        } catch (e: IOException) {
            e.printStackTrace()
            client.close()
        }

    }

    private suspend fun getMessages(client: Socket) {

        val out: PrintWriter?
        val `in`: BufferedReader?

        out = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream())), true)
        `in` = BufferedReader(InputStreamReader(client.getInputStream()))

        Log.i("TAG - Client", "Socket connected")

        if (count == 0) {
            out.println("AUTHORIZE anp8850@gmail.com ")
            Log.i("TAG - Client", "Message sent: ")
            count++

            readMessage(`in`)
        }else{
            readMessage(`in`)
            readMessage(`in`)
        }

        Log.i("TAG - Client", "Message Received: " + `in`.readLine())
        Log.i("TAG - Client", "Message Received: " + `in`.readLine())

        delay(5000L)
        getMessages(client)
    }

    private fun readMessage(`in`: BufferedReader){
        val userInfo = StringParser.parseData(`in`.readLine())

        if (userInfo is ArrayList<*>) {
            userUpdatedInfoLiveData.postValue(userInfo as ArrayList<UserInfo>?)
        } else if (userInfo is UserUpdatedInfo) {
            userInfoLiveData.postValue(userInfo)
        }
    }

}
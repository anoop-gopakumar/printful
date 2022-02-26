package com.wundermobility.carrental.printful.utils

import com.wundermobility.carrental.printful.models.UserInfo
import com.user.locationfinder.printful.musiclocationfinder.models.Places
import com.wundermobility.carrental.printful.models.UserUpdatedInfo
import java.util.regex.Pattern

object StringParser {

    fun parseData(data: String) : Any? {

        if (data.contains("USERLIST")) {

            val dataWithoutPrefix = data.removePrefix("USERLIST")

            val mainList: List<String> = Pattern.compile(";").split(dataWithoutPrefix).toList()

            val arrayList: ArrayList<UserInfo> = ArrayList()

            mainList.forEach {

                val list: List<String> = Pattern.compile(",").split(it).toList()

                arrayList.add(UserInfo().apply {
                    list.forEachIndexed { index, s ->
                        when (index) {
                            0 -> this.id = s.trim().toInt()
                            1 -> this.name = s
                            2 -> this.profileImage = s
                            3 -> this.latitude = s.toDouble()
                            4 -> this.longitude = s.toDouble()
                        }
                    }
                })
            }

            return arrayList

        } else if (data.contains("UPDATE")) {

            val dataWithoutPrefix = data.removePrefix("UPDATE")

            val list: List<String> = Pattern.compile(",").split(dataWithoutPrefix).toList()

            return UserUpdatedInfo().apply {
                list.forEachIndexed { index, s ->
                    when (index) {
                        0 -> this.id = s.trim().toInt()
                        1 -> this.latitude = s.toDouble()
                        2 -> this.longitude = s.toDouble()
                    }
                }
            }

        }

        return null
    }

}
/*
 * Copyright (C) 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.user.locationfinder.printful.musiclocationfinder.network

import com.user.locationfinder.printful.musiclocationfinder.models.PlacesHolder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.logging.HttpLoggingInterceptor

import com.google.gson.Gson
import retrofit2.Response
import okhttp3.Interceptor
import okhttp3.Request

private val SERVICE: MusicBrainzNetwork by lazy {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getInterceptor())
        .addInterceptor(UserAgentInterceptor())
        .build()

    //TODO integrate this API
    // https://musicbrainz.org/ws/2/place/?query=music&fmt=json&limit=20&offset=2

    val retrofit = Retrofit.Builder()
        .baseUrl("https://musicbrainz.org/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    retrofit.create(MusicBrainzNetwork::class.java)
}

fun getMusicBrainzService() = SERVICE

fun getInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    return HttpLoggingInterceptor()
}

class UserAgentInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest: Request = chain.request()
        val requestWithUserAgent: Request = originalRequest.newBuilder()
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.105 Safari/537.36"
            )
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface MusicBrainzNetwork {

    @GET("/ws/2/place/?fmt=json")
    suspend fun fetchPlaces(
        @Query("query") query: String,
        @Query("offset") offset : Int,
        @Query("limit") limit: Int,
    ): Response<PlacesHolder>

}



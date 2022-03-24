package com.abbas.test.truecaller.data.remote

import retrofit2.http.GET

interface RemoteApi {

    @GET("life-as-an-android-engineer")
    suspend fun get10thCharacter(): String

    @GET("life-as-an-android-engineer")
    suspend fun getEvery10thCharacter(): String

    @GET("life-as-an-android-engineer")
    suspend fun getSplitCharacter(): String

}
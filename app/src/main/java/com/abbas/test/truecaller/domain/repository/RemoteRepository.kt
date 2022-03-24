package com.abbas.test.truecaller.domain.repository

import retrofit2.http.GET

interface RemoteRepository {
    suspend fun get10thCharacter(): String

    suspend fun getEvery10thCharacter(): String

    suspend fun getSplitCharacter(): String

}
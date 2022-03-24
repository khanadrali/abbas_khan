package com.abbas.test.truecaller.data.remote

import com.abbas.test.truecaller.domain.repository.RemoteRepository

class RepositoryImpl(val remoteApi: RemoteApi) : RemoteRepository {
    override suspend fun get10thCharacter(): String {
        return remoteApi.get10thCharacter()
    }

    override suspend fun getEvery10thCharacter(): String {
        return remoteApi.getEvery10thCharacter()
    }

    override suspend fun getSplitCharacter(): String {
        return remoteApi.getSplitCharacter()
    }

}
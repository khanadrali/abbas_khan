package com.abbas.test.truecaller.domain.usecase

import android.content.res.loader.ResourcesLoader
import com.abbas.test.truecaller.domain.repository.RemoteRepository
import com.abbas.test.truecaller.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetEvery10thUseCase(val repository: RemoteRepository) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getEvery10thCharacter()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error("Error Message: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error Message: Check your Internet Connection"))
        }
    }
}
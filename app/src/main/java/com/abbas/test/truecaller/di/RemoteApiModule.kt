package com.abbas.test.truecaller.di

import android.content.Context
import com.abbas.test.truecaller.data.remote.RemoteApi
import com.abbas.test.truecaller.data.remote.RepositoryImpl
import com.abbas.test.truecaller.domain.repository.RemoteRepository
import com.abbas.test.truecaller.domain.usecase.Get10thLetterUseCase
import com.abbas.test.truecaller.domain.usecase.GetAlphabetUseCase
import com.abbas.test.truecaller.domain.usecase.GetEvery10thUseCase
import com.abbas.test.truecaller.domain.usecase.GetMainUseCases
import com.abbas.test.truecaller.util.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteApiModule {

    @Provides
    @Singleton
    fun getApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): RemoteApi {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient).build()
            .create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun getHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS))
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun getGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }


    @Provides
    @Singleton
    fun getMainUseCases(repository: RemoteRepository): GetMainUseCases {
        return GetMainUseCases(
            Get10thLetterUseCase(repository),
            GetEvery10thUseCase(repository),
            GetAlphabetUseCase(repository)
        )

    }


    @Provides
    @Singleton
    fun getRepository(remoteApi: RemoteApi): RemoteRepository {
        return RepositoryImpl(remoteApi)
    }

}
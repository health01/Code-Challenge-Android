package com.kst.testing.code_challenge_android.di

import com.google.gson.GsonBuilder
import com.kst.testing.code_challenge_android.network.PropertiesService
import com.kst.testing.code_challenge_android.repository.PropertiesRepository
import com.kst.testing.code_challenge_android.repository.PropertiesRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestAppModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(getTestURl())
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun providePropertiesService(retrofit: Retrofit): PropertiesService {
        return retrofit.create(PropertiesService::class.java)
    }

    @Singleton
    @Provides
    fun providePropertiesRepository(propertiesService: PropertiesService): PropertiesRepository =
        PropertiesRepositoryImpl(propertiesService)

    @Named("IO")
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.IO

    private fun getTestURl(): String {
        return "http://localhost:8080/"
    }
}
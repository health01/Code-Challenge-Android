package com.kst.testing.code_challenge_android.di

import com.google.gson.GsonBuilder
import com.kst.testing.code_challenge_android.network.PropertiesService
import com.kst.testing.code_challenge_android.repository.PropertiesRepository
import com.kst.testing.code_challenge_android.repository.PropertiesRepositoryImpl
import com.kst.testing.code_challenge_android.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        val gson = GsonBuilder()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

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
}
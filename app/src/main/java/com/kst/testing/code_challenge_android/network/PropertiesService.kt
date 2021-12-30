package com.kst.testing.code_challenge_android.network

import com.kst.testing.code_challenge_android.network.model.Properties
import retrofit2.http.GET
import retrofit2.http.Headers

interface PropertiesService {
    @GET("properties.json")
    suspend fun getProperties(): Properties
}
package com.kst.testing.code_challenge_android.mockData

import com.google.gson.Gson
import com.kst.testing.code_challenge_android.TestHelper
import com.kst.testing.code_challenge_android.network.PropertiesService
import com.kst.testing.code_challenge_android.network.model.Properties

class MockPropertiesService(private val fileName: String) : PropertiesService {
    override suspend fun getProperties(): Properties {
        val source = TestHelper.getJsonString(fileName)
        val gson = Gson()
        return gson.fromJson(source, Properties::class.java)
    }
}
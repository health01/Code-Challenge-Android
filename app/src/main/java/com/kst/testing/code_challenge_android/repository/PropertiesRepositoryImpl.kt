package com.kst.testing.code_challenge_android.repository

import com.kst.testing.code_challenge_android.network.PropertiesService
import com.kst.testing.code_challenge_android.network.model.Properties
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PropertiesRepositoryImpl @Inject constructor(private val propertiesApi: PropertiesService) :
    PropertiesRepository {

    override fun getPropertiesFlow(): Flow<Properties> = flow<Properties> {
        emit(propertiesApi.getProperties())
    }
}
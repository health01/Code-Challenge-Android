package com.kst.testing.code_challenge_android.repository

import com.kst.testing.code_challenge_android.network.model.Properties
import kotlinx.coroutines.flow.Flow

interface PropertiesRepository {
    fun getPropertiesFlow(): Flow<Properties>
}
package com.kst.testing.code_challenge_android.network.model

import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("properties")
    val propertyList: List<Property>? = null
)



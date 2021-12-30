package com.kst.testing.code_challenge_android.network.model

import com.google.gson.annotations.SerializedName


class Property(
    @SerializedName("address")
    val address: String? = null,

    @SerializedName("bathrooms")
    val bathrooms: Int? = null,

    @SerializedName("bedrooms")
    val bedrooms: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("number")
    val number: String? = null,

    @SerializedName("postcode")
    val postcode: String? = null,

    @SerializedName("price")
    val price: Int? = null,

    @SerializedName("propertyType")
    val propertyType: String? = null,

    @SerializedName("region")
    val region: String? = null
)
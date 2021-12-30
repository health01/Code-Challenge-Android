package com.kst.testing.code_challenge_android

import okio.Okio

object TestHelper {
    fun getJsonString(fileName: String = "properties.json"): String {
        val classloader = javaClass.classLoader
        val inputStream = classloader.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(Charsets.UTF_8)
    }
}
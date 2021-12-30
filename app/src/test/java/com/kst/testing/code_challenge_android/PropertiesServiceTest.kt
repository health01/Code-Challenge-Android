package com.kst.testing.code_challenge_android

import com.google.gson.JsonSyntaxException
import com.kst.testing.code_challenge_android.network.PropertiesService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class PropertiesServiceTest {
    private lateinit var service: PropertiesService
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PropertiesService::class.java)
    }

    @After
    fun dropdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `retrieve correct DataSet`() {
        enqueueResponse("properties.json")
        val response = runBlocking {
            service.getProperties()
        }
        val request = mockWebServer.takeRequest()
        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/properties.json"))


        MatcherAssert.assertThat(response, CoreMatchers.`is`(IsNull.notNullValue()))
        MatcherAssert.assertThat(response.propertyList, CoreMatchers.`is`(IsNull.notNullValue()))

        response.propertyList?.forEach { property ->
            MatcherAssert.assertThat(property.id, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(property.id, CoreMatchers.`is`(instanceOf(Int::class.java)))

            MatcherAssert.assertThat(property.bedrooms, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                property.bedrooms,
                CoreMatchers.`is`(instanceOf(Int::class.java))
            )

            MatcherAssert.assertThat(property.number, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                property.number,
                CoreMatchers.`is`(instanceOf(String::class.java))
            )

            MatcherAssert.assertThat(property.address, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                property.address,
                CoreMatchers.`is`(instanceOf(String::class.java))
            )

            MatcherAssert.assertThat(property.region, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                property.region,
                CoreMatchers.`is`(instanceOf(String::class.java))
            )


            MatcherAssert.assertThat(property.postcode, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                property.postcode,
                CoreMatchers.`is`(instanceOf(String::class.java))
            )

            MatcherAssert.assertThat(
                property.propertyType,
                CoreMatchers.`is`(IsNull.notNullValue())
            )
            MatcherAssert.assertThat(
                property.propertyType,
                CoreMatchers.`is`(instanceOf(String::class.java))
            )

        }
    }

    @Test(expected = JsonSyntaxException::class)
    fun `retrieve IllegalStateException when api return array`() {
        enqueueResponse("propertiesArray.json")


        runBlocking {
            service.getProperties()
        }

        val request = mockWebServer.takeRequest()
        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/propertiesArray.json"))
    }

    private fun enqueueResponse(fileName: String) {
        val source = TestHelper.getJsonString(fileName)
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse
                .setBody(source)
        )
    }
}
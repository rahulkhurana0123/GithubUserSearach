package com.rahul.data.network

import android.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ServiceTest {

    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private var service: ApiService? = null
    private var mockWebServer: MockWebServer? = null

    @Before
    @Throws(IOException::class)
    fun createdService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer!!.url("/"))
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java!!)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer!!.shutdown()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun testGetUser() {
        enqueueResponse("search/users")

        val request = mockWebServer!!.takeRequest()
    }




    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer!!.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(Service.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Service.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Service.READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }
}
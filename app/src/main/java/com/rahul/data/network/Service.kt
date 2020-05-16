package com.rahul.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rahul.gitsearch.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rahul khurana on 16.05.2020.
 */

object Service {


    val CONNECT_TIMEOUT : Long = 60

    val READ_TIMEOUT : Long = 60

    val WRITE_TIMEOUT : Long = 60

    var api: ApiService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()

        val client = httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        api = retrofit.create(ApiService::class.java)
    }
}

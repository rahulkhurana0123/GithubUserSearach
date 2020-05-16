package com.android.daggerandroid2

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rahul.data.network.ApiService
import com.rahul.di.component.AppComponent
import com.rahul.gitsearch.Config
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Rahul khurana on 2020-05-03.
 */
@Module
class MainModule{

    val CONNECT_TIMEOUT : Long = 60

    val READ_TIMEOUT : Long = 60

    val WRITE_TIMEOUT : Long = 60

    var builder : Retrofit.Builder

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()

        val client = httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build()

        builder = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    }


    @Provides
    @Singleton
    fun createService(): ApiService {
        val retrofit = builder.build()
        return retrofit.create(ApiService::class.java)
    }



}
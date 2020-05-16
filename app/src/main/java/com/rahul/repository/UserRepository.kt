package com.rahul.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.rahul.data.network.Service
import com.rahul.data.model.UsersDto
import com.rahul.data.network.ApiService
import kotlinx.coroutines.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by rahul khurana on 16.05.2020.
 */

class UserRepository (
        val usersData: MutableLiveData<UsersDto>,
        val error: MutableLiveData<String>
) {

    private val TAG: String = UserRepository::class.java.simpleName

    @Inject
    lateinit var apiService : ApiService

    fun searchUsersService(query: String = "") {

        apiService.getUser(query).enqueue(object : Callback<UsersDto?> {
            override fun onFailure(call: Call<UsersDto?>?, t: Throwable?) {
                error.postValue("Error found")
            }
            override fun onResponse(call: Call<UsersDto?>?, response: Response<UsersDto?>?) {

                if (response != null) {
                    if (response.isSuccessful) {
                        usersData.postValue(response.body())

                    } else {
                        error.postValue(response.errorBody()!!.string())
                    }
                }
            }
        })
    }


    fun searchUsersDeferredService(query: String){
        GlobalScope.launch {
            try {
                val response = Service.api.getDefer(query).await()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        usersData.value = response.body()
                    } else {
                        Log.e("Exception", "${response.errorBody()}")
                        error.value = response.errorBody()?.string()
                    }
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Exception", e.message)
                    error.value = e.message
                    error.postValue(e.message)
                }
            }
        }
    }


}
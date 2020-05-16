package com.rahul.ui.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rahul.data.model.UsersDto
import com.rahul.repository.UserRepository

/**
 * Created by rahul khurana on 16.05.2020.
 */

class MainViewModel: ViewModel(){

    private val TAG: String = MainViewModel::class.java.simpleName
    var userResponse: UserRepository

    val usersData: MutableLiveData<UsersDto> by lazy { MutableLiveData<UsersDto>() }
    val error : MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        userResponse = UserRepository(usersData, error)
    }

    fun fetchUsers(query: String = "") {
        userResponse.searchUsersDeferredService(query)

    }



}
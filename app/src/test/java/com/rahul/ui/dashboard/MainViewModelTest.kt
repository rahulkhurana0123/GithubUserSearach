package com.rahul.ui.dashboard

import android.arch.lifecycle.MutableLiveData
import com.rahul.data.model.UsersDto
import com.rahul.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @InjectMocks
    lateinit var mainViewModel: MainViewModel
    @Mock
    lateinit var userResponse: UserRepository
    @Mock
    lateinit var usersData: MutableLiveData<UsersDto>
    @Mock
    lateinit var error : MutableLiveData<String>

    @Test
    fun fetchUsers() {
        val query = "user_name"
        mainViewModel.fetchUsers(query)
        Mockito.verify(userResponse).searchUsersService(query)
    }
}
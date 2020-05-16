package com.rahul.data.model

/**
 * Created by rahul khurana on 16.05.2020.
 */

data class UsersDto(
        val total_count: Int,
        val incomplete_results: Boolean,
    val items: MutableList<UserModel>
)
package com.rahul.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by rahul khurana on 16.05.2020.
 */

data class UserModel(
    @SerializedName("url")
    var url: String,
    @SerializedName("login")
    var name: String,
    @SerializedName("bio") var bio: String,
    @SerializedName("avatar_url") var thumbnail: String
)
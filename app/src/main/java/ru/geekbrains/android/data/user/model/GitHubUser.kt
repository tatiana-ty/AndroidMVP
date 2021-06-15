package ru.geekbrains.android.data.user.model

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar: String
)
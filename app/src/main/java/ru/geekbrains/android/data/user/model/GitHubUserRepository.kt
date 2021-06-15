package ru.geekbrains.android.data.user.model

import com.google.gson.annotations.SerializedName

data class GitHubUserRepository(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("language") val language: String
)
package ru.geekbrains.android.data.user.datasource

import ru.geekbrains.android.data.api.GitHubApiFactory
import ru.geekbrains.android.data.user.datasource.cloud.CloudUserDataSource

object UserDataSourceFactory {

    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())

}
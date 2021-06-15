package ru.geekbrains.android.data.user.datasource.cache

import io.reactivex.Single
import ru.geekbrains.android.data.user.datasource.UserDataSource
import ru.geekbrains.android.data.user.model.GitHubUser

interface CacheUserDataSource : UserDataSource {

    fun retain(users: List<GitHubUser>): Single<List<GitHubUser>>

    fun retain(user: GitHubUser): Single<GitHubUser>
}
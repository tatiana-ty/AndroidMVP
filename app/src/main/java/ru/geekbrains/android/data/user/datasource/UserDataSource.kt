package ru.geekbrains.android.data.user.datasource

import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository

interface UserDataSource {

    fun getUsers(): Observable<List<GitHubUser>>

    fun getUserByLogin(login: String): Single<GitHubUser>

    fun getUserRepositories(login: String): Single<List<GitHubUserRepository>>

}
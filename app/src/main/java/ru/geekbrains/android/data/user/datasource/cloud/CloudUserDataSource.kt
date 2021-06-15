package ru.geekbrains.android.data.user.datasource.cloud

import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.android.data.api.GitHubApi
import ru.geekbrains.android.data.user.datasource.UserDataSource
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository

class CloudUserDataSource(private val gitHubApi: GitHubApi) : UserDataSource {

    override fun getUsers(): Observable<List<GitHubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(login: String): Single<GitHubUser> =
        gitHubApi.getUserByLogin(login)

    override fun getUserRepositories(login: String): Single<List<GitHubUserRepository>> =
        gitHubApi.getUserRepositories(login)

}
package ru.geekbrains.android.data.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository

interface GitHubApi {

    @GET("/users")
    fun getUsers(@Query("since") since: Int? = null): Observable<List<GitHubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(@Path("username") login: String): Single<GitHubUser>

    @GET("/users/{username}/repos")
    fun getUserRepositories(@Path("username") login: String): Single<List<GitHubUserRepository>>

}
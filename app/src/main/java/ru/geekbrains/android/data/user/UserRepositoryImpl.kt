package ru.geekbrains.android.data.user

import io.reactivex.Observable
import ru.geekbrains.android.data.user.datasource.UserDataSource
import ru.geekbrains.android.data.user.datasource.cache.CacheUserDataSource
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository

class UserRepositoryImpl(
    private val cloudUserDataSource: UserDataSource,
    private val cacheUserDataSource: CacheUserDataSource,
) : UserRepository {

    override fun getUsers(): Observable<List<GitHubUser>> =
        cacheUserDataSource
            .getUsers()
            .flatMap(::getFromCloudIfRequired)

    private fun getFromCloudIfRequired(users: List<GitHubUser>): Observable<List<GitHubUser>> =
        if (users.isEmpty()) {
            cloudUserDataSource
                .getUsers()
                .flatMapSingle(cacheUserDataSource::retain)
        } else {
            Observable.just(users)
        }

    override fun getUserByLogin(login: String): Observable<GitHubUser> =
        Observable.concat(
            cacheUserDataSource
                .getUserByLogin(login)
                .toObservable(),
            cloudUserDataSource
                .getUserByLogin(login)
                .flatMap(cacheUserDataSource::retain)
                .toObservable()
        )

    override fun getUserRepositories(login: String): Observable<List<GitHubUserRepository>> =
        cloudUserDataSource
            .getUserRepositories(login)
            .toObservable()
}
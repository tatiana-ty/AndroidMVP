package ru.geekbrains.android.data.user.datasource.cache

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository

class CacheUserDataSourceImpl : CacheUserDataSource {

    private val cache = mutableListOf<GitHubUser>()
    private val cacheRepos = mutableListOf<GitHubUserRepository>()

    override fun getUsers(): Observable<List<GitHubUser>> =
        Observable.just(cache)

    override fun getUserByLogin(login: String): Single<GitHubUser> =
        Single.defer {
            cache.firstOrNull { it.login == login }?.let { Single.just(it) }
                ?: Single.error(RuntimeException("Нет такого пользователя"))
        }

    override fun getUserRepositories(login: String): Single<List<GitHubUserRepository>> =
        Single.just(cacheRepos)

    override fun retain(users: List<GitHubUser>): Single<List<GitHubUser>> =
        Single.fromCallable {
            cache.clear()
            cache.addAll(users)
            cache
        }

    override fun retain(user: GitHubUser): Single<GitHubUser> =
        Single.fromCallable {
            cache.add(user)
            user
        }

}

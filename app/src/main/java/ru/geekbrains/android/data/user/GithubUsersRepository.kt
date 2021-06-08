package ru.geekbrains.android.data.user

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.android.data.user.model.GithubUser

class GithubUsersRepository (private val users: List<GithubUser>) : UserRepository {

    override fun getUsers() : Single<List<GithubUser>> =
        Single.just(users)

    override fun getUserByLogin(login: String): Single<GithubUser> {
        var githubUser: GithubUser? = null
        for (user in users) {
            if (user.login == login) githubUser = user
        }
        return Single.just(githubUser)
    }
}
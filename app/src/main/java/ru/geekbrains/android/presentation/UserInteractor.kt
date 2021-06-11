package ru.geekbrains.android.presentation

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.android.data.user.UserRepository
import ru.geekbrains.android.data.user.model.GithubUser

class UserInteractor (private val userRepository: UserRepository) {

    fun getUsers(): Single<List<GithubUser>> =
        userRepository
            .getUsers()
            .onErrorReturnItem(emptyList())

    fun getUserByLogin(login: String): @NonNull Maybe<GithubUser>? =
        userRepository
            .getUserByLogin(login)
            .onErrorReturnItem(GithubUser(""))

}
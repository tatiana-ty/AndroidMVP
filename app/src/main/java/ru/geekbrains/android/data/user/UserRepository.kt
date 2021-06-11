package ru.geekbrains.android.data.user

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.android.data.user.model.GithubUser

interface UserRepository {

    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String): Maybe<GithubUser>

}
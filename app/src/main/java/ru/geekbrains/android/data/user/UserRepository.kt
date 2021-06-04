package ru.geekbrains.android.data.user

import ru.geekbrains.android.data.user.model.GithubUser

interface UserRepository {

    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    fun getUsers(): List<GithubUser>

}
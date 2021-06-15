package ru.geekbrains.android.data.user

import io.reactivex.Observable
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository

interface UserRepository {

    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    fun getUsers(): Observable<List<GitHubUser>>

    /**
     * Возвращает пользователя по логину.
     * @param login логин пользователя
     * @return пользователь
     */
    fun getUserByLogin(login: String): Observable<GitHubUser>

    fun getUserRepositories(login: String): Observable<List<GitHubUserRepository>>

}
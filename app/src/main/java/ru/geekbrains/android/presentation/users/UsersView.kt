package ru.geekbrains.android.presentation.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.android.data.user.model.GitHubUser

interface UsersView : MvpView {

    /**
     * Показывает список пользователей.
     * @param users список пользователей
     */
    @SingleState
    fun showUsers(users: List<GitHubUser>)

    /**
     * Показывает ошибку.
     * @param message сообщение об ошибке
     */
    @SingleState
    fun showError(message: String?)

    /**
     * Показывает процесс загрузки.
     */
    @SingleState
    fun showLoading()
}
package ru.gb.gb_popular_libs.lession2.presentation.user

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.android.data.user.model.GitHubUser

interface UserView : MvpView {

    /**
     * Показывает пользователя.
     * @param user пользователь
     */
    @SingleState
    fun showUser(user: GitHubUser)

    /**
     * Показывает ошибку.
     * @param message сообщение об ошибке
     */
    @SingleState
    fun showError(message: String?)
}
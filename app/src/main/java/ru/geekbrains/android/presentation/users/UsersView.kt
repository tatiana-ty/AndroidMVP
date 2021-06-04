package ru.geekbrains.android.presentation.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.android.data.user.model.GithubUser

interface UsersView : MvpView {

    /**
     * Показывает список пользователей.
     * @param users список пользователей
     */
    @SingleState
    fun showUsers(users: List<GithubUser>)

}
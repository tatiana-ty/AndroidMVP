package ru.geekbrains.android.presentation.repo

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.android.data.user.model.GitHubUserRepository

interface RepoView : MvpView {

    @SingleState
    fun showRepo(repo: GitHubUserRepository)

    /**
     * Показывает ошибку.
     * @param message сообщение об ошибке
     */
    @SingleState
    fun showError(message: String?)
}
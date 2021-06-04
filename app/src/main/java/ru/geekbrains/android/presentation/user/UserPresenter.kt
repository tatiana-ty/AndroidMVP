package ru.geekbrains.android.presentation.user

import moxy.MvpPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.geekbrains.android.data.user.UserRepository
import ru.geekbrains.android.data.user.model.GithubUser

class UserPresenter(
    private val userLogin: String,
    private val userRepository: UserRepository
): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        val users = userRepository.getUsers()
        var githubUser: GithubUser? = null
        for (user in users) {
            if (user.login == userLogin) githubUser = user
        }
        githubUser!!
            .let(viewState::showUser)
    }

}
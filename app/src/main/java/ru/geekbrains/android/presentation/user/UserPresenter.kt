package ru.geekbrains.android.presentation.user

import moxy.MvpPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.presentation.UserInteractor

class UserPresenter(
    private val userLogin: String,
    private val interactor: UserInteractor
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        interactor
            .getUserByLogin(userLogin)
            .subscribe(
                ::onSuccess,
                ::onError
            )
    }

    private fun onSuccess(user: GithubUser){
        user.let(viewState::showUser)
    }

    private fun onError(error: Throwable) {

    }
}
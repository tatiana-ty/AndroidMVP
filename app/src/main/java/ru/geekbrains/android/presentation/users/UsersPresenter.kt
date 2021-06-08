package ru.geekbrains.android.presentation.users

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.presentation.UserInteractor
import ru.geekbrains.android.presentation.user.UserScreen

class UsersPresenter(
    private val interactor: UserInteractor,
    private val router: Router
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        interactor
            .getUsers()
            .subscribe(
                ::onSuccess,
                ::onError
            )
    }

    fun displayUser(user: GithubUser) =
        router.navigateTo(UserScreen(user.login))

    private fun onSuccess(users: List<GithubUser>) {
        viewState.showUsers(users)
    }

    private fun onError(error: Throwable) {

    }
}
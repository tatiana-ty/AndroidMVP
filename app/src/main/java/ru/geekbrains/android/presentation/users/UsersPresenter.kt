package ru.geekbrains.android.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.presentation.UserInteractor
import ru.geekbrains.android.presentation.user.UserScreen

class UsersPresenter(
    private val interactor: UserInteractor,
    private val router: Router
) : MvpPresenter<UsersView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            interactor
                .getUsers()
                .subscribe(
                    ::onSuccess,
                    ::onError
                )
        )
    }

    fun displayUser(user: GithubUser) =
        router.navigateTo(UserScreen(user.login))

    private fun onSuccess(users: List<GithubUser>) {
        viewState.showUsers(users)
    }

    private fun onError(error: Throwable) {
        viewState.showError(error.message)
        router.exit()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}
package ru.geekbrains.android.presentation.user

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.presentation.UserInteractor

class UserPresenter(
    private val userLogin: String,
    private val interactor: UserInteractor,
    private val router: Router
) : MvpPresenter<UserView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            interactor
                .getUserByLogin(userLogin)
                ?.subscribe(
                    ::onSuccess,
                    ::onError
                )
        )
    }

    private fun onSuccess(user: GithubUser){
        user.let(viewState::showUser)
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
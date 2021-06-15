package ru.geekbrains.android.presentation.user

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.geekbrains.android.data.user.UserRepository
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.presentation.repo.RepoScreen
import ru.geekbrains.android.schedulers.Schedulers

class UserPresenter(
    private val userId: String,
    private val userRepository: UserRepository,
    private val schedulers: Schedulers,
    private val router: Router
) : MvpPresenter<UserView>() {

    private var disposables = io.reactivex.disposables.CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            userRepository
                .getUserByLogin(userId)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    ::onFetchUserByIdSuccess,
                    ::onFetchUserByIdError
                )
        )
    }

    private fun onFetchUserByIdSuccess(user: GitHubUser) {
        viewState.showUser(user)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onFetchUserByIdError(error: Throwable) {
        viewState.showError(error.message)

        /*
         * Возвращаемся на предыдущий
         * экран в случае ошибки.
         */
        router.exit()
    }

    fun displayRepo(repo: GitHubUserRepository) =
        router.navigateTo(RepoScreen(repo.name))

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}
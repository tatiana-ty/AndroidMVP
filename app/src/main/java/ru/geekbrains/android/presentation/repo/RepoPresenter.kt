package ru.geekbrains.android.presentation.repo

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.geekbrains.android.data.user.UserRepository
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.schedulers.Schedulers

class RepoPresenter(
    private val userName: String,
    private val repoName: String,
    private val userRepository: UserRepository,
    private val schedulers: Schedulers,
    private val router: Router
) : MvpPresenter<RepoView>() {

    private var disposables = io.reactivex.disposables.CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            userRepository
                .getRepoByName(userName, repoName)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    ::onSuccess,
                    ::onError
                )
        )
    }

    private fun onSuccess(repo: GitHubUserRepository) {
        viewState.showRepo(repo)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onError(error: Throwable) {
        viewState.showError(error.message)

        /*
         * Возвращаемся на предыдущий
         * экран в случае ошибки.
         */
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}
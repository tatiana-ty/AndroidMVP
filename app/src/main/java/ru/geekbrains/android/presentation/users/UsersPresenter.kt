package ru.geekbrains.android.presentation.users

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.android.NetworkState
import ru.geekbrains.android.data.network.NetworkStateRepository
import ru.geekbrains.android.data.user.UserRepository
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.presentation.user.UserScreen
import ru.geekbrains.android.schedulers.Schedulers

class UsersPresenter(
    private val userRepository: UserRepository,
    private val networkStateRepository: NetworkStateRepository,
    private val router: Router,
    private val schedulers: Schedulers,
): MvpPresenter<UsersView>() {

    private var disposables = io.reactivex.disposables.CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            networkStateRepository
                .watchForNetworkState()
                .filter { networkState -> networkState == NetworkState.CONNECTED }
                .observeOn(schedulers.main())
                .doOnNext { displayUsers() }
                .subscribeOn(schedulers.background())
                .subscribe()
        )

        displayUsers()
    }

    private fun displayUsers() {
        viewState.showLoading()

        disposables.add(
            userRepository
                .getUsers()
                .map { users -> users.map(UserMapper::map) }
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    ::onSuccess,
                    ::onError
                )
        )
    }

    private fun onSuccess(users: List<GitHubUser>) {
        viewState.showUsers(users)
    }

    private fun onError(error: Throwable) {
        viewState.showError(error.message)
    }

    fun displayUser(user: GitHubUser) =
        router.navigateTo(UserScreen(user.login))

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}
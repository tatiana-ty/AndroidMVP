package ru.geekbrains.android.presentation.users

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.android.data.user.UserRepository
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.presentation.user.UserScreen

class UsersPresenter(
    private val userRepository: UserRepository,
    private val router: Router
): MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        viewState.showUsers(userRepository.getUsers())
    }

    fun displayUser(user: GithubUser) =
        router.navigateTo(UserScreen(user.login))

}
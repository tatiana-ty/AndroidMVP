package ru.geekbrains.android.presentation.user

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.geekbrains.android.R.layout.fragment_user
import ru.geekbrains.android.arguments
import ru.geekbrains.android.data.user.UserRepositoryFactory
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.databinding.FragmentUserBinding
import ru.geekbrains.android.presentation.UserInteractor

class UserFragment : MvpAppCompatFragment(fragment_user), UserView {

    companion object {
        private const val ARG_USER_LOGIN = "userLogin"

        fun newInstance(userLogin: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_LOGIN to userLogin)
    }

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN) ?: ""
    }

    @Suppress("unused")
    private val presenter by moxyPresenter {
        UserPresenter(
            userLogin,
            interactor = UserInteractor(UserRepositoryFactory.create())
        )
    }

    private val binding by viewBinding(FragmentUserBinding::bind)

    override fun showUser(user: GithubUser) {
        binding.userLogin.text = user.login
    }

}
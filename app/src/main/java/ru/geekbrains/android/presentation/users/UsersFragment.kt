package ru.geekbrains.android.presentation.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.android.GithubApplication.Navigation.router
import ru.geekbrains.android.R.layout.fragment_users
import ru.geekbrains.android.arguments
import ru.geekbrains.android.data.user.UserRepositoryFactory
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.databinding.FragmentUsersBinding
import ru.geekbrains.android.presentation.UserInteractor
import ru.geekbrains.android.presentation.users.adapter.UsersAdapter

class UsersFragment : MvpAppCompatFragment(fragment_users), UsersView, UsersAdapter.Delegate {

    companion object {
        fun newInstance(): Fragment =
            UsersFragment()
                .arguments()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            interactor = UserInteractor(UserRepositoryFactory.create()),
            router = router
        )
    }

    private val binding by viewBinding(FragmentUsersBinding::bind)

    private val usersAdapter = UsersAdapter(delegate = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.users.adapter = usersAdapter
    }

    override fun showUsers(users: List<GithubUser>) =
        usersAdapter.submitList(users)

    override fun onUserPicked(user: GithubUser) =
        presenter.displayUser(user)

}
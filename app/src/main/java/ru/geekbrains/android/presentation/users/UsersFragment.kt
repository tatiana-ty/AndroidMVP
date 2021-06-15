package ru.geekbrains.android.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.gb_popular_libs.scheduler.SchedulersFactory
import ru.geekbrains.android.GithubApplication.Navigation.router
import ru.geekbrains.android.R.layout.fragment_users
import ru.geekbrains.android.arguments
import ru.geekbrains.android.data.network.NetworkStateRepositoryFactory
import ru.geekbrains.android.data.user.UserRepositoryFactory
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.databinding.FragmentUsersBinding
import ru.geekbrains.android.presentation.users.adapter.UsersAdapter

class UsersFragment : MvpAppCompatFragment(fragment_users), UsersView, UsersAdapter.Delegate {

    companion object {

        fun newInstance(): Fragment =
            UsersFragment()
                .arguments()

    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            userRepository = UserRepositoryFactory.create(),
            networkStateRepository = NetworkStateRepositoryFactory.create(),
            router = router,
            schedulers = SchedulersFactory.create()
        )
    }

    private var viewBinding: FragmentUsersBinding? = null

    private val usersAdapter = UsersAdapter(delegate = this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUsersBinding
            .inflate(inflater, container, false)
            .apply {
                viewBinding = this
                viewBinding?.users?.adapter = usersAdapter
            }
            .root

    override fun showLoading() {
        viewBinding?.loading?.visibility = View.VISIBLE
        viewBinding?.users?.visibility = View.GONE
    }

    override fun showUsers(users: List<GitHubUser>) {
        usersAdapter.submitList(users)

        viewBinding?.loading?.visibility = View.GONE
        viewBinding?.users?.visibility = View.VISIBLE
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUserPicked(user: GitHubUser) =
        presenter.displayUser(user)

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}
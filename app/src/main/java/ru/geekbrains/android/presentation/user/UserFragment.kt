package ru.geekbrains.android.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.gb_popular_libs.lession2.presentation.user.UserView
import ru.gb.gb_popular_libs.scheduler.SchedulersFactory
import ru.geekbrains.android.GithubApplication
import ru.geekbrains.android.R.layout.fragment_user
import ru.geekbrains.android.arguments
import ru.geekbrains.android.data.user.UserRepositoryFactory
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.databinding.FragmentUserBinding
import ru.geekbrains.android.presentation.user.adapter.ReposAdapter

class UserFragment : MvpAppCompatFragment(fragment_user), UserView, ReposAdapter.Delegate {

    companion object {

        private const val ARG_USER_ID = "userId"

        fun newInstance(userLogin: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_ID to userLogin)

    }

    private val userId: String by lazy {
        arguments?.getString(ARG_USER_ID) ?: ""
    }

    @Suppress("unused")
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userId,
            userRepository = UserRepositoryFactory.create(),
            schedulers = SchedulersFactory.create(),
            router = GithubApplication.Navigation.router
        )
    }

    private var viewBinding: FragmentUserBinding? = null

    private val reposAdapter = ReposAdapter(delegate = this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUserBinding
            .inflate(inflater, container, false)
            .apply {
                viewBinding = this
                viewBinding?.repos?.adapter = reposAdapter
            }
            .root

    override fun showRepos(repos: List<GitHubUserRepository>) {
        reposAdapter.submitList(repos)
        viewBinding?.repos?.visibility = View.VISIBLE
    }

    override fun showUser(user: GitHubUser) {
        viewBinding?.userId?.text = user.id
        viewBinding?.userName?.text = user.login
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onRepoPicked(repo: GitHubUserRepository) =
        presenter.displayRepo(repo)


}
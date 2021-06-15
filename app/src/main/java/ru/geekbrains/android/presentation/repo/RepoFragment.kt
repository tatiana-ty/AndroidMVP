package ru.geekbrains.android.presentation.repo

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
import ru.geekbrains.android.R
import ru.geekbrains.android.arguments
import ru.geekbrains.android.data.user.UserRepositoryFactory
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.databinding.FragmentUserBinding
import ru.geekbrains.android.presentation.user.UserFragment
import ru.geekbrains.android.presentation.user.UserPresenter
import ru.geekbrains.android.presentation.user.adapter.ReposAdapter

class RepoFragment : MvpAppCompatFragment(R.layout.fragment_repo), UserView {

    companion object {

        private const val ARG_REPO_NAME = "repoName"

        fun newInstance(repoName: String): Fragment =
            UserFragment()
                .arguments(ARG_REPO_NAME to repoName)

    }

    private val userId: String by lazy {
        arguments?.getString(ARG_REPO_NAME) ?: ""
    }

    @Suppress("unused")
    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            userId,
            userRepository = UserRepositoryFactory.create(),
            schedulers = SchedulersFactory.create(),
            router = GithubApplication.Navigation.router
        )
    }

    private var viewBinding: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUserBinding
            .inflate(inflater, container, false)
            .apply { viewBinding = this }
            .root

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

}
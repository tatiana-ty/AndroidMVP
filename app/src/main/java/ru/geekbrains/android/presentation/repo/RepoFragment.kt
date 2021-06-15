package ru.geekbrains.android.presentation.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import ru.geekbrains.android.R
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.databinding.FragmentRepoBinding

class RepoFragment(private val repo: GitHubUserRepository) :
    MvpAppCompatFragment(R.layout.fragment_repo) {

    companion object {

        fun newInstance(repo: GitHubUserRepository): Fragment = RepoFragment(repo)
    }

    private var viewBinding: FragmentRepoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRepoBinding
            .inflate(inflater, container, false)
            .apply { viewBinding = this }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.repoId?.text = repo.id
        viewBinding?.repoName?.text = repo.name
        viewBinding?.repoDescription?.text = repo.description
        viewBinding?.repoLanguage?.text = repo.language
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}
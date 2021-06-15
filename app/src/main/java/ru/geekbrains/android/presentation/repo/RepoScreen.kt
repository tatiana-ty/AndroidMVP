package ru.geekbrains.android.presentation.repo

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.android.data.user.model.GitHubUserRepository

class RepoScreen (private val repo: GitHubUserRepository) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        RepoFragment.newInstance(repo = repo)

}
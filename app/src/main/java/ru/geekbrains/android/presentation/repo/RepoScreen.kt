package ru.geekbrains.android.presentation.repo

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RepoScreen (private val repoName: String) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        RepoFragment.newInstance(repoName = repoName)

}
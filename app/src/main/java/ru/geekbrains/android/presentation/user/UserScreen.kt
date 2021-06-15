package ru.geekbrains.android.presentation.user

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen(private val userLogin: String) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        UserFragment.newInstance(userLogin = userLogin)

}
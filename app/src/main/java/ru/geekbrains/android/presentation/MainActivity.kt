package ru.geekbrains.android.presentation

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import ru.geekbrains.android.GithubApplication.Navigation.navigatorHolder
import ru.geekbrains.android.GithubApplication.Navigation.router
import ru.geekbrains.android.R
import ru.geekbrains.android.R.layout.activity_main
import ru.geekbrains.android.presentation.users.UsersScreen

class MainActivity : MvpAppCompatActivity(activity_main) {

    private val navigator = AppNavigator(activity = this, R.id.screen_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(UsersScreen)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}

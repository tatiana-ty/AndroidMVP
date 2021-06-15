package ru.geekbrains.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone

class GithubApplication : Application() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder { lateinit var context: Context }

    object Navigation {

        private val cicerone by lazy { Cicerone.create() }

        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()

    }

    override fun onCreate() {
        super.onCreate()

        ContextHolder.context = applicationContext
        //RxJavaPlugins.setErrorHandler { error -> Log.e("GLOBAL_ERRORS", error.message.toString()) }
    }

}
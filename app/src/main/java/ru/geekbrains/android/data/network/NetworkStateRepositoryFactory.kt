package ru.geekbrains.android.data.network

import ru.geekbrains.android.GithubApplication.ContextHolder.context

object NetworkStateRepositoryFactory {
    
    fun create(): NetworkStateRepository = NetworkStateRepositoryImpl(context)

}
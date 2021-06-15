package ru.geekbrains.android.data.user

import ru.geekbrains.android.data.user.datasource.UserDataSourceFactory
import ru.geekbrains.android.data.user.datasource.cache.CacheUserDataSourceFactory

object UserRepositoryFactory {

    private val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            UserDataSourceFactory.create(),
            CacheUserDataSourceFactory.create()
        )
    }

    fun create(): UserRepository = userRepository

}
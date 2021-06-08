package ru.geekbrains.android.data.user

object UserRepositoryFactory {

    fun create(): UserRepository = GithubUsersRepository(GithubUsers.users)

}
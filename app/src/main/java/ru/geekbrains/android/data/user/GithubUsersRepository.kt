package ru.geekbrains.android.data.user

import ru.geekbrains.android.data.user.model.GithubUser

class GithubUsersRepository (private val users: List<GithubUser>) : UserRepository {

    override fun getUsers() : List<GithubUser> = users
}
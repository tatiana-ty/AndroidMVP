package ru.geekbrains.android.presentation.users.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.android.data.user.model.GithubUser

object UserDiffing : DiffUtil.ItemCallback<GithubUser>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.login == newItem.login
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: GithubUser, newItem: GithubUser) = payload

}


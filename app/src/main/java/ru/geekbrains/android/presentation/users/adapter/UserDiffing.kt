package ru.geekbrains.android.presentation.users.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.android.data.user.model.GitHubUser

object UserDiffing : DiffUtil.ItemCallback<GitHubUser>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: GitHubUser, newItem: GitHubUser) = payload

}


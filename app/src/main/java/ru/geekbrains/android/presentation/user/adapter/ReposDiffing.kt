package ru.geekbrains.android.presentation.user.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.android.data.user.model.GitHubUserRepository

object ReposDiffing : DiffUtil.ItemCallback<GitHubUserRepository>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: GitHubUserRepository, newItem: GitHubUserRepository): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: GitHubUserRepository, newItem: GitHubUserRepository): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: GitHubUserRepository, newItem: GitHubUserRepository) = payload

}


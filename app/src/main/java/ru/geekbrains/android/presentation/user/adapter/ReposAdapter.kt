package ru.geekbrains.android.presentation.user.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.databinding.ViewRepoBinding
import ru.geekbrains.android.databinding.ViewUserBinding

class ReposAdapter(private val delegate: Delegate?) :
    ListAdapter<GitHubUserRepository, ReposViewHolder>(ReposDiffing) {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * пользователя из списка.
         * @param user пользователь
         */
        fun onRepoPicked(repo: GitHubUserRepository)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder =
        ReposViewHolder(
            ViewRepoBinding
                .inflate(from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}
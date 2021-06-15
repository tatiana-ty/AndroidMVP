package ru.geekbrains.android.presentation.user.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.android.click
import ru.geekbrains.android.data.user.model.GitHubUserRepository
import ru.geekbrains.android.databinding.ViewRepoBinding

class ReposViewHolder(
    private val viewBinding: ViewRepoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(repo: GitHubUserRepository, delegate: ReposAdapter.Delegate?) {
        with(viewBinding) {
            repoName.text = repo.name

            root.click { delegate?.onRepoPicked(repo) }
        }
    }

}
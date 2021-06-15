package ru.geekbrains.android.presentation.users.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.android.click
import ru.geekbrains.android.data.user.model.GitHubUser
import ru.geekbrains.android.databinding.ViewUserBinding
import ru.geekbrains.android.setCircleImageFromUri

class UserViewHolder(
    private val viewBinding: ViewUserBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(user: GitHubUser, delegate: UsersAdapter.Delegate?) {
        with(viewBinding) {
            userAvatar.setCircleImageFromUri(user.avatar)
            userId.text = user.login

            root.click { delegate?.onUserPicked(user) }
        }
    }

}
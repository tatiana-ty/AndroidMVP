package ru.geekbrains.android.presentation.users.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.android.click
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.databinding.ViewUserBinding

class UserViewHolder(
    private val viewBinding: ViewUserBinding
): RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(user: GithubUser, delegate: UsersAdapter.Delegate?) {
        viewBinding.userLogin.text = user.login
        viewBinding.root.click { delegate?.onUserPicked(user) }
    }

}
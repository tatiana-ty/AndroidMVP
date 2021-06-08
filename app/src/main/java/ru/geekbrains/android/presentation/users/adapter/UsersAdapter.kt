package ru.geekbrains.android.presentation.users.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.android.data.user.model.GithubUser
import ru.geekbrains.android.databinding.ViewUserBinding

class UsersAdapter(private val delegate: Delegate?): ListAdapter<GithubUser, UserViewHolder>(UserDiffing) {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * пользователя из списка.
         * @param user пользователь
         */
        fun onUserPicked(user: GithubUser)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ViewUserBinding
                .inflate(from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}
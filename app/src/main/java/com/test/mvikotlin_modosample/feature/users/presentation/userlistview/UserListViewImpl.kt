package com.test.mvikotlin_modosample.feature.users.presentation.userlistview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.mvikotlin_modosample.databinding.ItemUserBinding
import com.test.mvikotlin_modosample.databinding.ViewUserListBinding
import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListView.Event
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListView.Model

class UserListViewImpl(
    root: ViewGroup, attachToRoot: Boolean = true
) : BaseMviView<Model, Event>(), UserListView {

    private val viewBinding: ViewUserListBinding = ViewUserListBinding.inflate(
        LayoutInflater.from(root.context), root, attachToRoot
    )

    private val adapter = ListDelegationAdapter(usersDelegate(
        userClickListener = { login ->
            dispatch(Event.UserClicked(login = login))
        }
    ))

    init {
        viewBinding.recyclerUserList.adapter = adapter
    }

    override val renderer: ViewRenderer<Model> = diff {
        diff(get = Model::users, set = ::setUsers)
        diff(get = Model::loading, set = ::setLoading)
    }

    private fun setUsers(users: List<UserModel>) {

        with(adapter) {
            items = users
            notifyDataSetChanged()
        }
    }

    private fun setLoading(loading: Boolean) {
        viewBinding.progress.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun usersDelegate(userClickListener: (login: String) -> Unit) =
        adapterDelegateViewBinding<UserModel, UserModel, ItemUserBinding>(
            { layoutInflater, parent -> ItemUserBinding.inflate(layoutInflater, parent, false) }
        ) {
            binding.root.setOnClickListener {
                userClickListener.invoke(item.login)
            }

            bind {
                binding.ivAvatar.load(item.avatar) {
                    transformations(CircleCropTransformation())
                }

                binding.tvLogin.text = item.login
            }
        }
}
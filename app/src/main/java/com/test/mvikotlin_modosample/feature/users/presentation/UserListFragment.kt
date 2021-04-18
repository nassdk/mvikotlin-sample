package com.test.mvikotlin_modosample.feature.users.presentation

import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.test.mvikotlin_modosample.R
import com.test.mvikotlin_modosample.databinding.ScreenUserListBinding
import com.test.mvikotlin_modosample.feature.global.presentation.BaseFragment
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListConnections
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListViewImpl
import org.koin.android.ext.android.inject

class UserListFragment : BaseFragment(R.layout.screen_user_list) {

    private val viewBinding: ScreenUserListBinding by viewBinding()
    private val store: UserListStore by scopeInject()

    override fun prepareUi() {

        UserListViewImpl(root = viewBinding.root)
            .bind(store, UserListConnections)
    }

    override fun initBindings() {

        bind(this@UserListFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.labels.bindTo { label ->
                when (label) {
                    is UserListStore.Label.Error -> showError(message = label.error)
                }
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
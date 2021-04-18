package com.test.mvikotlin_modosample.feature.userdetails.presentation

import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.test.mvikotlin_modosample.R
import com.test.mvikotlin_modosample.databinding.ScreenUserDetailsBinding
import com.test.mvikotlin_modosample.feature.global.ext.withArgs
import com.test.mvikotlin_modosample.feature.global.presentation.BaseFragment
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainConnections
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainViewImpl
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarConnections
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarViewImpl
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserDetailsFragment : BaseFragment(R.layout.screen_user_details) {

    private val viewBinding: ScreenUserDetailsBinding by viewBinding()
    private val store: UserDetailsStore by inject { parametersOf(userLogin) }

    private val userLogin by lazy {
        arguments?.getString(ARG_USER_LOGIN, "").orEmpty()
    }

    override fun prepareUi() {

        UserDetailsToolbarViewImpl(root = viewBinding.toolbarContainer)
            .bind(store, UserDetailsToolbarConnections)

        UserDetailsMainViewImpl(root = viewBinding.mainContainer)
            .bind(store, UserDetailsMainConnections)
    }

    override fun initBindings() {

        bind(this@UserDetailsFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.labels.bindTo { label ->
                when (label) {
                    is UserDetailsStore.Label.Error -> showError(message = label.message)
                }
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val ARG_USER_LOGIN = "ARG_USER_LOGIN"

        fun newInstance(login: String) = UserDetailsFragment().withArgs {
            putString(ARG_USER_LOGIN, login)
        }
    }
}
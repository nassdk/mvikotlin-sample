package com.test.mvikotlin_modosample.feature.users.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel
import com.test.mvikotlin_modosample.feature.users.presentation.UserListStore.*

interface UserListStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class UserClicked(val login: String) : Intent()
    }

    sealed class Label {
        data class Error(val error: String) : Label()
    }

    data class State(
        val users: List<UserModel> = emptyList(),
        val loading: Boolean = false
    )
}
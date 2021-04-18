package com.test.mvikotlin_modosample.feature.users.presentation.userlistview

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListView.*

interface UserListView : MviView<Model, Event> {

    data class Model(
        val users: List<UserModel>,
        val loading: Boolean
    )

    sealed class Event {
        data class UserClicked(val login: String) : Event()
    }
}
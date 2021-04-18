package com.test.mvikotlin_modosample.feature.users.presentation.userlistview

import com.test.mvikotlin_modosample.feature.global.presentation.ViewConnections
import com.test.mvikotlin_modosample.feature.users.presentation.UserListStore.Intent
import com.test.mvikotlin_modosample.feature.users.presentation.UserListStore.State
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListView.Event
import com.test.mvikotlin_modosample.feature.users.presentation.userlistview.UserListView.Model

object UserListConnections : ViewConnections<State, Intent, Model, Event> {

    override val stateToModel: (State) -> Model = { state ->
        Model(
            users = state.users,
            loading = state.loading
        )
    }


    override val eventToIntent: (Event) -> Intent = { event ->
        when (event) {
            is Event.UserClicked -> Intent.UserClicked(login = event.login)
        }
    }
}
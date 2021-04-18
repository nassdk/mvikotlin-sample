package com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar

import com.test.mvikotlin_modosample.feature.global.presentation.ViewConnections
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStore.Intent
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStore.State
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarView.Event
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarView.Model

object UserDetailsToolbarConnections : ViewConnections<State, Intent, Model, Event> {

    override val stateToModel: (State) -> Model = { state ->
        Model(login = state.userInformation?.login.orEmpty())
    }

    override val eventToIntent: (Event) -> Intent = { event ->
        when (event) {
            Event.OnBackPressed -> Intent.OnBackPressed
        }
    }
}
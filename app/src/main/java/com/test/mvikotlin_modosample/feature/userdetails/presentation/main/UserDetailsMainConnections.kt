package com.test.mvikotlin_modosample.feature.userdetails.presentation.main

import com.test.mvikotlin_modosample.feature.global.presentation.ViewConnections
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStore.Intent
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStore.State
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainView.Event
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainView.Model

object UserDetailsMainConnections : ViewConnections<State, Intent, Model, Event> {

    override val stateToModel: (State) -> Model = { state ->
        Model(
            loading = state.loading,
            userDetails = state.userInformation
        )
    }

    override val eventToIntent: (Event) -> Intent = {
        Intent.Idle
    }
}
package com.test.mvikotlin_modosample.feature.userdetails.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStore.*

interface UserDetailsStore : Store<Intent, State, Label> {

    sealed class Intent {
        object OnBackPressed : Intent()
        object Idle : Intent()
    }

    sealed class Label {
        data class Error(val message: String) : Label()
    }

    data class State(
        val userInformation: UserDetailsModel? = null,
        val loading: Boolean = false
    )
}
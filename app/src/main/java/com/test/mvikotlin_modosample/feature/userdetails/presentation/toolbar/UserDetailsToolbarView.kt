package com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarView.Event
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarView.Model

interface UserDetailsToolbarView : MviView<Model, Event> {

    data class Model(
        val login: String
    )

    sealed class Event {
        object OnBackPressed : Event()
    }
}
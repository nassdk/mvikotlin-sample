package com.test.mvikotlin_modosample.feature.userdetails.presentation.main

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainView.Event
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainView.Model

interface UserDetailsMainView : MviView<Model, Event> {

    data class Model(
        val loading: Boolean,
        val userDetails: UserDetailsModel?
    )

    class Event
}
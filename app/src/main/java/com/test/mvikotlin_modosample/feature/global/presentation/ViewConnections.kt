package com.test.mvikotlin_modosample.feature.global.presentation

interface ViewConnections<State : Any, Intent : Any, Model : Any, Event : Any> {

    val stateToModel: (State) -> Model

    val eventToIntent: (Event) -> Intent
}
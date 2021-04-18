package com.test.mvikotlin_modosample.feature.userdetails.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.back
import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel
import com.test.mvikotlin_modosample.feature.userdetails.domain.usecase.GetUserDetailsUseCase
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStore.*

class UserDetailsStoreFactory(
    private val loadUseDetailsUseCase: GetUserDetailsUseCase,
    private val storeFactory: StoreFactory,
    private val modo: Modo,
    private val userLogin: String
) {

    fun create(): UserDetailsStore =
        object : UserDetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "UserDetailStoreFactory",
            initialState = State(),
            bootstrapper = BootstrapperImpl,
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private object BootstrapperImpl : SuspendBootstrapper<Action>() {

        override suspend fun bootstrap() {
            dispatch(Action.LoadUserDetails)
        }
    }

    sealed class Action {
        object LoadUserDetails : Action()
    }

    private inner class ExecutorImpl : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.LoadUserDetails -> loadUserDetails()
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Idle -> Unit
                Intent.OnBackPressed -> modo.back()
            }
        }

        private suspend fun loadUserDetails() {

            dispatch(Result.Loading)

            try {
                val userDetails = loadUseDetailsUseCase.invoke(login = userLogin)
                dispatch(Result.UserDetailsLoaded(model = userDetails))
            } catch (e: Exception) {
                publish(Label.Error(message = e.localizedMessage ?: "Произошла ошибка"))
            } finally {
                dispatch(Result.StopLoading)
            }
        }
    }

    private object ReducerImpl : Reducer<State, Result> {

        override fun State.reduce(result: Result) = when (result) {
            Result.Loading -> copy(loading = true)
            Result.StopLoading -> copy(loading = false)
            is Result.UserDetailsLoaded -> copy(userInformation = result.model)
        }
    }

    sealed class Result {
        object Loading : Result()
        object StopLoading : Result()
        data class UserDetailsLoaded(val model: UserDetailsModel) : Result()
    }
}
package com.test.mvikotlin_modosample.feature.users.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.forward
import com.test.mvikotlin_modosample.feature.global.navigation.Screens
import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel
import com.test.mvikotlin_modosample.feature.users.domain.usecase.LoadUsersListUseCase
import com.test.mvikotlin_modosample.feature.users.presentation.UserListStore.*

class UserListStoreFactory(
    private val storeFactory: StoreFactory,
    private val loadUsersUseCase: LoadUsersListUseCase,
    private val modo: Modo
) {
    fun create(): UserListStore =
        object : UserListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "UserListStoreFactory",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl()
        ) {}

    private inner class BootstrapperImpl : SuspendBootstrapper<Action>() {

        override suspend fun bootstrap() {
            dispatch(Action.LoadUsers)
        }
    }

    sealed class Action {
        object LoadUsers : Action()
    }

    private inner class ExecutorImpl : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.UserClicked -> modo.forward(Screens.UserDetails(login = intent.login))
            }
        }

        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.LoadUsers -> loadUsers()
            }
        }

        private suspend fun loadUsers() {

            dispatch(Result.Loading)

            try {
                val users = loadUsersUseCase.invoke()
                dispatch(Result.UsersLoaded(users = users))
            } catch (e: Exception) {
                publish(Label.Error(error = e.localizedMessage ?: "Ошибка загрузки данных"))
            } finally {
                dispatch(Result.StopLoading)
            }
        }
    }

    private inner class ReducerImpl : Reducer<State, Result> {

        override fun State.reduce(result: Result) = when (result) {
            is Result.Loading -> copy(loading = true)
            is Result.StopLoading -> copy(loading = false)
            is Result.UsersLoaded -> copy(users = result.users)
        }
    }

    sealed class Result {
        data class UsersLoaded(val users: List<UserModel>) : Result()
        object Loading : Result()
        object StopLoading : Result()
    }
}
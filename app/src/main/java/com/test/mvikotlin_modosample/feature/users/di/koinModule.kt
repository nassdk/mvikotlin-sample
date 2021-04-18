package com.test.mvikotlin_modosample.feature.users.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.test.mvikotlin_modosample.feature.global.ext.store
import com.test.mvikotlin_modosample.feature.users.data.network.UsersMapper
import com.test.mvikotlin_modosample.feature.users.data.repository.UsersRepositoryImpl
import com.test.mvikotlin_modosample.feature.users.domain.repository.UsersRepository
import com.test.mvikotlin_modosample.feature.users.domain.usecase.LoadUsersListUseCase
import com.test.mvikotlin_modosample.feature.users.presentation.UserListFragment
import com.test.mvikotlin_modosample.feature.users.presentation.UserListStoreFactory
import org.koin.dsl.module

val usersFeatureModule = module {

    single { UsersMapper() }
    single<UsersRepository> {
        UsersRepositoryImpl(
            api = get(),
            mapper = get()
        )
    }

    single { LoadUsersListUseCase(repository = get()) }

//    scope<UserListFragment> {
        single {
            UserListStoreFactory(
                storeFactory = DefaultStoreFactory,
                loadUsersUseCase = get(),
                modo = get()
            ).create()
        }
//    }
}
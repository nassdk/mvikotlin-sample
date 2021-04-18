package com.test.mvikotlin_modosample.feature.userdetails.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.test.mvikotlin_modosample.feature.global.ext.store
import com.test.mvikotlin_modosample.feature.userdetails.data.network.UserDetailsMapper
import com.test.mvikotlin_modosample.feature.userdetails.data.repository.UserDetailsRepositoryImpl
import com.test.mvikotlin_modosample.feature.userdetails.domain.repository.UserDetailsRepository
import com.test.mvikotlin_modosample.feature.userdetails.domain.usecase.GetUserDetailsUseCase
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsFragment
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsStoreFactory
import org.koin.dsl.module

val userDetailsFeatureModule = module {

    single { UserDetailsMapper() }
    single<UserDetailsRepository> {
        UserDetailsRepositoryImpl(
            api = get(),
            mapper = get()
        )
    }

    single { GetUserDetailsUseCase(repository = get()) }

    scope<UserDetailsFragment> {
        store { (userLogin: String) ->
            UserDetailsStoreFactory(
                storeFactory = DefaultStoreFactory,
                userLogin = userLogin,
                loadUseDetailsUseCase = get(),
                modo = get()
            ).create()
        }
    }
}
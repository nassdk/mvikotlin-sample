package com.test.mvikotlin_modosample.di

import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiReducer
import com.github.terrakok.modo.android.AppReducer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val navigationModule = module {

    single {
        Modo(AppReducer(context = androidContext(), origin = MultiReducer()))
    }
}
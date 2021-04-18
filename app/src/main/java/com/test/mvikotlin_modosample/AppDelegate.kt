package com.test.mvikotlin_modosample

import android.app.Application
import com.test.mvikotlin_modosample.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initKoin()
    }

    private fun initKoin() {

        startKoin {
            androidContext(this@AppDelegate)
            modules(koinModules)
        }
    }

    private fun initTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
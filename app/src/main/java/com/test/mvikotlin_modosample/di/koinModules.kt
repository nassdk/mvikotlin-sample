package com.test.mvikotlin_modosample.di

import com.test.mvikotlin_modosample.feature.global.network.di.dataModule
import com.test.mvikotlin_modosample.feature.userdetails.di.userDetailsFeatureModule
import com.test.mvikotlin_modosample.feature.users.di.usersFeatureModule

val koinModules = listOf(
    dataModule,
    navigationModule,
    usersFeatureModule,
    userDetailsFeatureModule
)
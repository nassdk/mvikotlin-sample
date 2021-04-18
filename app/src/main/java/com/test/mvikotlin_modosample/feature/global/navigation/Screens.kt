package com.test.mvikotlin_modosample.feature.global.navigation

import com.github.terrakok.modo.android.AppScreen
import com.github.terrakok.modo.android.MultiAppScreen
import com.test.mvikotlin_modosample.feature.userdetails.presentation.UserDetailsFragment
import com.test.mvikotlin_modosample.feature.users.presentation.UserListFragment
import kotlinx.android.parcel.Parcelize
import kotlin.math.log

object Screens {

    @Parcelize
    class Users : AppScreen("Users") {
        override fun create() = UserListFragment()
    }

    @Parcelize
    data class UserDetails(val login: String) : AppScreen("UserDetails") {
        override fun create() = UserDetailsFragment.newInstance(login = login)
    }

    fun MultiStack() = MultiAppScreen(
        id = "MultiStack identifier",
        roots = listOf(Users(), Users()),
        selected = 0
    )
}
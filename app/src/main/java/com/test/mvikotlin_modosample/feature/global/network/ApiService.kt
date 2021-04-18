package com.test.mvikotlin_modosample.feature.global.network

import com.test.mvikotlin_modosample.feature.userdetails.data.network.UserDetailsNetModel
import com.test.mvikotlin_modosample.feature.users.data.network.UserNetModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun loadUsers(): List<UserNetModel>

    @GET("users/{login}")
    suspend fun loadUserDetails(
        @Path("login") login: String
    ): UserDetailsNetModel
}
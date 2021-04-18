package com.test.mvikotlin_modosample.feature.userdetails.domain.repository

import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel

interface UserDetailsRepository {
    suspend fun loadUserDetails(login: String): UserDetailsModel
}
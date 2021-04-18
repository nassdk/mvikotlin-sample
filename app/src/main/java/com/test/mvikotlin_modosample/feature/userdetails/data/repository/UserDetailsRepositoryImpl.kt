package com.test.mvikotlin_modosample.feature.userdetails.data.repository

import com.test.mvikotlin_modosample.feature.global.network.ApiService
import com.test.mvikotlin_modosample.feature.userdetails.data.network.UserDetailsMapper
import com.test.mvikotlin_modosample.feature.userdetails.domain.repository.UserDetailsRepository

class UserDetailsRepositoryImpl(
    private val api: ApiService,
    private val mapper: UserDetailsMapper
) : UserDetailsRepository {

    override suspend fun loadUserDetails(login: String) = mapper.map(
        api.loadUserDetails(login = login)
    )
}
package com.test.mvikotlin_modosample.feature.users.data.repository

import com.test.mvikotlin_modosample.feature.global.network.ApiService
import com.test.mvikotlin_modosample.feature.users.data.network.UsersMapper
import com.test.mvikotlin_modosample.feature.users.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val api: ApiService,
    private val mapper: UsersMapper
) : UsersRepository {

    override suspend fun loadUsers() = mapper.map(api.loadUsers())
}
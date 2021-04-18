package com.test.mvikotlin_modosample.feature.users.domain.repository

import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel

interface UsersRepository {
    suspend fun loadUsers(): List<UserModel>
}
package com.test.mvikotlin_modosample.feature.users.domain.usecase

import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel
import com.test.mvikotlin_modosample.feature.users.domain.repository.UsersRepository

class LoadUsersListUseCase(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(): List<UserModel> {
        return repository.loadUsers()
    }
}
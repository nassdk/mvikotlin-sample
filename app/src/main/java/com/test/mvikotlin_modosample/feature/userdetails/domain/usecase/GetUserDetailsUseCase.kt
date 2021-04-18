package com.test.mvikotlin_modosample.feature.userdetails.domain.usecase

import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel
import com.test.mvikotlin_modosample.feature.userdetails.domain.repository.UserDetailsRepository

class GetUserDetailsUseCase(
    private val repository: UserDetailsRepository
) {
    suspend operator fun invoke(login: String): UserDetailsModel {
        return repository.loadUserDetails(login = login)
    }
}
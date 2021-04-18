package com.test.mvikotlin_modosample.feature.userdetails.data.network

import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel

class UserDetailsMapper {

    fun map(model: UserDetailsNetModel) = model.run {
        UserDetailsModel(
            id = id,
            login = login,
            avatar = avatar,
            name = name,
            company = company
        )
    }
}
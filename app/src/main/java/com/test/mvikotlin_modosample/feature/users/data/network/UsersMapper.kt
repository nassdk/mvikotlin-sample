package com.test.mvikotlin_modosample.feature.users.data.network

import com.test.mvikotlin_modosample.feature.users.domain.model.UserModel

class UsersMapper {

    fun map(users: List<UserNetModel>) = users.map { user ->
        user.run {
            UserModel(
                id = id,
                login = login,
                avatar = avatar
            )
        }
    }
}
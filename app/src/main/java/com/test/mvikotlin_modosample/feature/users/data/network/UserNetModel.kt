package com.test.mvikotlin_modosample.feature.users.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserNetModel(
    @SerialName("id") val id: String,
    @SerialName("login") val login: String,
    @SerialName("avatar_url") val avatar: String
)
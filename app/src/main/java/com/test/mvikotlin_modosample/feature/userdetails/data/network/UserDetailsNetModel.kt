package com.test.mvikotlin_modosample.feature.userdetails.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsNetModel(
    @SerialName("id") val id: String,
    @SerialName("login") val login: String,
    @SerialName("avatar_url") val avatar: String,
    @SerialName("name") val name: String,
    @SerialName("company") val company: String
)
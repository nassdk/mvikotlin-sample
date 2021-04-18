package com.test.mvikotlin_modosample.feature.userdetails.domain.model

data class UserDetailsModel(
    val id: String,
    val login: String,
    val avatar: String,
    val name: String,
    val company: String
)
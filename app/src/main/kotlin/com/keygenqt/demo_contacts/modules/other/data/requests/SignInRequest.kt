package com.keygenqt.demo_contacts.modules.other.data.requests

data class SignInRequest(
    val username: String,
    val password: String,
    val rememberMe: Boolean = true,
)
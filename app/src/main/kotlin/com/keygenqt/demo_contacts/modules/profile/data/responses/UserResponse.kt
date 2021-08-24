package com.keygenqt.demo_contacts.modules.profile.data.responses

import androidx.compose.runtime.Immutable

@Immutable
data class UserCustomerResponse(
    val customer: UserResponse,
)

@Immutable
data class UserResponse(
    val uid: String,
    val name: String,
    val email: String,
)
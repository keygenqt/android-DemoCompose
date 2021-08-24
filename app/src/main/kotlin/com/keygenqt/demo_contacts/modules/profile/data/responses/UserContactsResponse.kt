package com.keygenqt.demo_contacts.modules.profile.data.responses

import androidx.compose.runtime.Immutable

@Immutable
data class UserContactsResponse(
    val email: UserContactsEmailResponse?,
    val phone: UserContactsPhoneResponse?,
)

@Immutable
data class UserContactsEmailResponse(
    val confirmed: Boolean?,
    val email: String?,
)

@Immutable
data class UserContactsPhoneResponse(
    val confirmed: Boolean?,
    val phone: String?,
)
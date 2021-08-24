/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.keygenqt.demo_contacts.modules.profile.data.responses

import androidx.compose.runtime.Immutable

@Immutable
data class UserContactsResponse(
    val email: UserContactsEmailResponse,
    val phone: UserContactsPhoneResponse,
)

@Immutable
data class UserContactsEmailResponse(
    val confirmed: Boolean?,
    val email: String?,
    val notifyMailShort: Boolean?,
    val notifyMailFull: Boolean?,
)

@Immutable
data class UserContactsPhoneResponse(
    val confirmed: Boolean?,
    val phone: String?,
    val notifySmsShort: Boolean?,
    val notifySmsFull: Boolean?,
)
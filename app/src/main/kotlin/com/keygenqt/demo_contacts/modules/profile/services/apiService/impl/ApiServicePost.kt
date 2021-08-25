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

package com.keygenqt.demo_contacts.modules.profile.services.apiService.impl

import com.keygenqt.demo_contacts.base.ResponseResult
import com.keygenqt.demo_contacts.base.Result422
import com.keygenqt.demo_contacts.base.executeWithResponse
import com.keygenqt.demo_contacts.base.responseCheck
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactsModel
import com.keygenqt.demo_contacts.modules.profile.data.requests.UserContactEmailRequest
import com.keygenqt.demo_contacts.modules.profile.data.requests.UserContactPhoneRequest
import com.keygenqt.demo_contacts.modules.profile.data.requests.UserContactRequest
import com.keygenqt.demo_contacts.modules.profile.services.api.ApiProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ApiServicePost {

    val api: ApiProfile

    suspend fun updateUserContacts(userContact: UserContactsModel): ResponseResult<Boolean> {
        return withContext(Dispatchers.IO) {
            executeWithResponse {
                api.updateUserContacts(UserContactRequest(
                    email = UserContactEmailRequest(
                        confirmed = userContact.email.confirmedContactEmail,
                        email = userContact.email.contactEmail,
                        notifyMailFull = userContact.email.notifyMailFull,
                        notifyMailShort = userContact.email.notifyMailShort,
                    ),
                    phone = UserContactPhoneRequest(
                        confirmed = userContact.phone.confirmedContactPhone,
                        phone = userContact.phone.contactPhone,
                        notifySmsFull = userContact.phone.notifySmsFull,
                        notifySmsShort = userContact.phone.notifySmsShort,
                    ),
                ))
                    .responseCheck()
                    .body() != null
            }
        }
    }

    suspend fun checkCode(emailOrPhone: String, code: String): ResponseResult<Boolean> {
        return withContext(Dispatchers.IO) {
            executeWithResponse {
                api.checkCode(
                    configGroupCode = "update",
                    contact = emailOrPhone.replace(" ", ""),
                    code = code
                )
                    .responseCheck()
                    .body()
                    ?.getAsJsonPrimitive("confirmedSuccess")?.asBoolean ?: false
            }
        }
    }

    suspend fun sendCode(emailOrPhone: String): ResponseResult<Boolean> {
        return withContext(Dispatchers.IO) {
            executeWithResponse {
                api.sendCode(
                    configGroupCode = "update",
                    contact = emailOrPhone.replace(" ", ""),
                )
                    .responseCheck {
                        it.body()?.let { obj ->
                            if (obj.getAsJsonPrimitive("sentSuccess")?.asBoolean == false) {
                                throw Result422(obj.getAsJsonPrimitive("errorMsg").asString)
                            }
                        }
                        it
                    }
                    .body()
                    ?.getAsJsonPrimitive("sentSuccess")?.asBoolean ?: false
            }
        }
    }
}
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

package com.keygenqt.demo_contacts.modules.profile.services.api.impl

import com.google.gson.JsonObject
import com.keygenqt.demo_contacts.modules.profile.data.requests.UserContactRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiPost {

    @POST("customers/current/contacts")
    suspend fun updateUserContacts(@Body request: UserContactRequest): Response<Any>

    @POST("customers/current/contacts/check-code")
    suspend fun checkCode(
        @Query("configGroupCode") configGroupCode: String,
        @Query("contact") contact: String,
        @Query("code") code: String,
    ): Response<JsonObject>

    @POST("customers/current/contacts/send-code")
    suspend fun sendCode(
        @Query("configGroupCode") configGroupCode: String,
        @Query("contact") contact: String,
    ): Response<JsonObject>
}
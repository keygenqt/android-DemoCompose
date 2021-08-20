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
 
package com.keygenqt.demo_contacts.modules.other.services.apiService.impl

import com.keygenqt.demo_contacts.base.ResponseResult
import com.keygenqt.demo_contacts.base.executeWithResponse
import com.keygenqt.demo_contacts.base.responseCheck
import com.keygenqt.demo_contacts.modules.other.data.requests.SignInRequest
import com.keygenqt.demo_contacts.modules.other.data.responses.SignInResponse
import com.keygenqt.demo_contacts.modules.other.services.api.ApiOther
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ApiServicePost {

    val api: ApiOther

    suspend fun signIn(login: String, passw: String): ResponseResult<SignInResponse> {
        return withContext(Dispatchers.IO) {
            executeWithResponse {
                api.signIn(SignInRequest(
                    username = login,
                    password = passw,
                ))
                    .responseCheck()
                    .body()!!
            }
        }
    }
}
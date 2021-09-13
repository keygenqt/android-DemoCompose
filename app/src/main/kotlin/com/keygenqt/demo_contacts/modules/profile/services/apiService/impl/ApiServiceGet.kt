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

import com.keygenqt.demo_contacts.BuildConfig
import com.keygenqt.demo_contacts.modules.profile.data.mappers.toModel
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactsModel
import com.keygenqt.demo_contacts.modules.profile.data.models.UserModel
import com.keygenqt.demo_contacts.modules.profile.services.api.ApiProfile
import com.keygenqt.demo_contacts.utils.ConstantsApp
import com.keygenqt.response.LocalTryExecuteWithResponse.executeWithResponse
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.responseCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface ApiServiceGet {

    val api: ApiProfile

    suspend fun getUser(): ResponseResult<UserModel> {
        return withContext(Dispatchers.IO) {
            if (BuildConfig.DEBUG) delay(ConstantsApp.DEBUG_DELAY) // Simulate slow internet
            executeWithResponse {
                api.getUser()
                    .responseCheck()
                    .body()
                    ?.toModel()!!
            }
        }
    }

    suspend fun getUserContacts(): ResponseResult<UserContactsModel> {
        return withContext(Dispatchers.IO) {
            if (BuildConfig.DEBUG) delay(ConstantsApp.DEBUG_DELAY) // Simulate slow internet
            executeWithResponse {
                api.getUserContacts()
                    .responseCheck()
                    .body()
                    ?.toModel()!!
            }
        }
    }
}
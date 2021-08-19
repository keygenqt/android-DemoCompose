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
 
package com.keygenqt.demo_contacts.modules.brands.services.apiService.impl

import com.keygenqt.demo_contacts.BuildConfig
import com.keygenqt.demo_contacts.base.ResponseResult
import com.keygenqt.demo_contacts.base.executeWithResponse
import com.keygenqt.demo_contacts.modules.brands.data.mappers.toModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel
import com.keygenqt.demo_contacts.modules.brands.services.api.ApiBrands
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface ApiServiceGet {

    val api: ApiBrands

    suspend fun getFeed(): ResponseResult<FeedModel?> {
        return withContext(Dispatchers.IO) {
            if (BuildConfig.DEBUG) delay(1000L) // Simulate slow internet
            executeWithResponse {
                api.getFeed()
                    .body()
                    ?.toModel()
            }
        }
    }
}
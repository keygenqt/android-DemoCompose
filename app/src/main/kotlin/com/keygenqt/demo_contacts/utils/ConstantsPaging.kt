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
 
package com.keygenqt.demo_contacts.utils

import java.util.concurrent.TimeUnit

object ConstantsPaging {

    // lists refresh once per hour
    val CACHE_TIMEOUT = TimeUnit.HOURS.toMillis(1)

    // const for api query
    const val MAX_PAGE_SIZE = 100
    const val PER_PAGE = 5
}
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

import com.keygenqt.demo_contacts.BuildConfig

object ConstantsApp {

    const val REFRESH_DELAY = 30000L

    const val DEBUG_DELAY = 1000L

    val API_URL_BASE
        get() =
            if (BuildConfig.DEBUG)
                "https://api.c5ia1s20aa-aromaluxe1-s1-public.model-t.cc.commerce.ondemand.com"
            else
                "https://api.c5ia1s20aa-aromaluxe1-p1-public.model-t.cc.commerce.ondemand.com"

    val API_URL get() = "$API_URL_BASE/rg/v1/newRG/"


    val DEBUG_CREDENTIAL_LOGIN get() = if (BuildConfig.DEBUG) "zarubin@surfstudio.ru" else ""
    val DEBUG_CREDENTIAL_PASSW get() = if (BuildConfig.DEBUG) "" else ""
}
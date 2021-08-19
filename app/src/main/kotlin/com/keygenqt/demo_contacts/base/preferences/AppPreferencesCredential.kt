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

package com.keygenqt.demo_contacts.base.preferences

import android.content.SharedPreferences

interface AppPreferencesBase {

    val p: SharedPreferences

    enum class KEYS {
        ACCESS_TOKEN,
        REFRESH_TOKEN,
        IS_START_PAGE,
    }

    var accessToken: String
        get() = p.getString(KEYS.ACCESS_TOKEN.name, null) ?: ""
        set(value) = p.edit().putString(KEYS.ACCESS_TOKEN.name, value).apply()

    var refreshToken: String
        get() = p.getString(KEYS.REFRESH_TOKEN.name, null) ?: ""
        set(value) = p.edit().putString(KEYS.REFRESH_TOKEN.name, value).apply()

    var isStartPage: Boolean
        get() = p.getBoolean(KEYS.IS_START_PAGE.name, true)
        set(value) = p.edit().putBoolean(KEYS.IS_START_PAGE.name, value).apply()
}
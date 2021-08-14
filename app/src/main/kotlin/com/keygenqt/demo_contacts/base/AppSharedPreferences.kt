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

package com.keygenqt.demo_contacts.base

import java.util.*

class AppSharedPreferences(private val p: android.content.SharedPreferences) {

    enum class KEYS {
        // common
        TOKEN,

        // last update list
        LAST_UPDATE_LIST_FAVORITE,
    }

    var token: String
        get() = p.getString(KEYS.TOKEN.name, "") ?: ""
        set(value) = p.edit().putString(KEYS.TOKEN.name, Base64.getEncoder().encodeToString("$value:".toByteArray()))
            .apply()

    var lastUpdateListFavorite: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST_FAVORITE.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST_FAVORITE.name, value).apply()
}
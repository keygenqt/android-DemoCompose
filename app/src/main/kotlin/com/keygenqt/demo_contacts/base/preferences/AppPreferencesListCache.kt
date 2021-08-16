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

interface AppPreferencesListCache {

    val p: SharedPreferences

    enum class KEYS {
        LAST_UPDATE_LIST_FAVORITE,
    }

    var lastUpdateListFavorite: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST_FAVORITE.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST_FAVORITE.name, value).apply()
}
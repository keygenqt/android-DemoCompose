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
 
package com.keygenqt.demo_contacts.extensions

import androidx.compose.ui.graphics.Color
import org.json.JSONArray
import org.json.JSONObject

fun String.toColor(): Color {
    return Color(
        when {
            this.contains("0xFF") -> android.graphics.Color.parseColor("#" + this.removePrefix("0xFF"))
            this.contains("#") -> android.graphics.Color.parseColor(this)
            else -> android.graphics.Color.parseColor("#$this")
        }
    )
}

fun String.parseApiError(): String? {
    return try {
        // fix for app
        val body = if (startsWith("{") && JSONObject(this).has("errors")) {
            JSONObject(this).getJSONArray("errors").toString()
        } else {
            this
        }
        // get json object
        val obj = if (body.startsWith("[")) {
            val arr = JSONArray(body)
            if (arr.length() == 0) throw Exception()
            arr.getJSONObject(0)
        } else {
            JSONObject(body)
        }
        // get message
        return if (obj.has("message")) {
            obj.getString("message")
        } else throw Exception()
    } catch (ex: Exception) {
        null
    }
}
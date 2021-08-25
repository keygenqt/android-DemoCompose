package com.keygenqt.demo_contacts.extensions

import okhttp3.ResponseBody
import org.json.JSONObject

fun ResponseBody.toJsonObject(): JSONObject {
    return JSONObject(string())
}
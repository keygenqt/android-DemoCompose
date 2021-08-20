package com.keygenqt.demo_contacts.extensions

import retrofit2.Response

fun <T> Response<T>.parseApiError(): String? {
    return this.errorBody()?.string()?.let {
        it.parseApiError()
    } ?: this.body().toString().parseApiError()
}
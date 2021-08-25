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

import androidx.paging.PagingSource
import com.keygenqt.demo_contacts.base.HTTPResult.*
import com.keygenqt.demo_contacts.base.interfaces.IModel
import com.keygenqt.demo_contacts.extensions.parseApiError
import com.keygenqt.demo_contacts.extensions.toHTTPResult
import retrofit2.Response

sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Exception) : ResponseResult<Nothing>()
}

inline fun <T> executeWithResponse(body: () -> T): ResponseResult<T> {
    return try {
        ResponseResult.Success(body.invoke())
    } catch (e: Exception) {
        ResponseResult.Error(e)
    }
}

val ResponseResult<*>?.size
    get() = if (this != null
        && this is ResponseResult.Success
        && data != null
        && data is List<*>
    ) {
        data.size
    } else if (this != null
        && this is ResponseResult.Success
        && data != null
    ) {
        1
    } else {
        0
    }

val ResponseResult<*>?.isEmpty
    get() = this.size == 0

val ResponseResult<*>?.isSucceeded get() = this != null && this is ResponseResult.Success && data != null

val ResponseResult<*>?.isError get() = this != null && this is ResponseResult.Error

inline infix fun <T, Value : Any> ResponseResult<T>.pagingSucceeded(
    predicate: (data: T) -> PagingSource.LoadResult<Int, Value>,
): PagingSource.LoadResult<Int, Value> {
    return if (this is ResponseResult.Success && this.data != null) {
        predicate.invoke(this.data)
    } else {
        if (this is ResponseResult.Error) {
            PagingSource.LoadResult.Error(this.exception)
        } else {
            PagingSource.LoadResult.Error(RuntimeException("Error response"))
        }
    }
}

inline infix fun <T> ResponseResult<T>.success(predicate: (data: T) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Success && this.data != null) {
        predicate.invoke(this.data)
    }
    return this
}

inline infix fun <T> ResponseResult<T>.error(predicate: (data: Exception) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Error) {
        predicate.invoke(this.exception)
    }
    return this
}

inline infix fun <T> ResponseResult<T>.done(predicate: () -> Unit): ResponseResult<T> {
    predicate.invoke()
    return this
}

fun ResponseResult<*>?.isEndDouble(lastStateId: String?) = this != null
        && this is ResponseResult.Success
        && data is List<*>
        && data.isNotEmpty()
        && data.last() is IModel
        && (data.last() as IModel).id == lastStateId


fun <T> Response<T>.responseCheck(check200: (Response<T>) -> Response<T> = { it }): Response<T> {
    return when (code().toHTTPResult()) {
        is Result200 -> check200.invoke(this)
        is Result400 -> throw Result400()
        is Result401 -> throw Result401()
        is Result403 -> throw this.parseApiError()?.let { Result403(it) } ?: Result403()
        is Result404 -> throw Result404()
        is Result500 -> throw Result500()
        is ResultUnknown -> throw this.parseApiError()?.let { ResultUnknown(code(), it) } ?: ResultUnknown(code())
    }
}
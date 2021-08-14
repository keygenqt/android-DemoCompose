package com.keygenqt.demo_contacts.base

import androidx.paging.PagingSource

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
package com.keygenqt.demo_contacts.base

import androidx.room.withTransaction
import com.keygenqt.demo_contacts.data.AppDatabase

interface BaseDataService<T : Any> {

    val db: AppDatabase
    val preferences: AppSharedPreferences

    @Suppress("UNCHECKED_CAST")
    suspend fun withTransaction(body: suspend T.() -> Unit) {
        db.withTransaction {
            body.invoke(this as T)
        }
    }
}
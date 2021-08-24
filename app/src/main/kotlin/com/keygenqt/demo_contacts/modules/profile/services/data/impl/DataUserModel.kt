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

package com.keygenqt.demo_contacts.modules.profile.services.data.impl

import com.keygenqt.demo_contacts.base.AppDatabase
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.modules.profile.data.dao.DaoUserModel
import com.keygenqt.demo_contacts.modules.profile.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface DataUserModel {
    val db: AppDatabase
    val preferences: AppPreferences

    private val dao: DaoUserModel get() = db.daoUserModel()

    suspend fun updateUser(model: UserModel) {
        dao.clear()
        dao.insertModels(*listOf(model).toTypedArray())
    }

    fun getUser(): Flow<UserModel?> {
        return dao.getModel()
    }

    suspend fun isEmpty(): Boolean {
        return dao.count() == 0
    }
}
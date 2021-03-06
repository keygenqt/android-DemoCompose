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
 
package com.keygenqt.demo_contacts.modules._common.services

import com.keygenqt.demo_contacts.base.AppDatabase
import com.keygenqt.demo_contacts.base.BaseDataService
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.modules.favorite.data.dao.DaoFavoriteModel
import com.keygenqt.demo_contacts.modules.profile.data.dao.DaoUserContactsModel
import com.keygenqt.demo_contacts.modules.profile.data.dao.DaoUserModel

class DataServiceCommon(
    override val db: AppDatabase,
    override val preferences: AppPreferences,
) : BaseDataService<DataServiceCommon> {

    private val daoFavorite: DaoFavoriteModel get() = db.daoFavorite()
    private val daoUser: DaoUserModel get() = db.daoUserModel()
    private val daoUserContacts: DaoUserContactsModel get() = db.daoUserContactsModel()

    suspend fun clearAfterLogout() {
        daoUserContacts.clear()
        daoFavorite.clear()
        daoUser.clear()
    }
}
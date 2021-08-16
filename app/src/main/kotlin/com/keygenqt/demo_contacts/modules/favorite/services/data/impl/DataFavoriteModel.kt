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

package com.keygenqt.demo_contacts.modules.favorite.services.data.impl

import androidx.paging.PagingSource
import com.keygenqt.demo_contacts.base.AppDatabase
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.modules.favorite.data.dao.DaoFavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel

interface DataFavoriteModel {
    val db: AppDatabase
    val preferences: AppPreferences

    private val dao: DaoFavoriteModel get() = db.daoFavorite()

    fun pagingList(): PagingSource<Int, FavoriteModel> {
        return dao.pagingSource()
    }

    suspend fun insert(models: List<FavoriteModel>) {
        dao.insertModels(models)
    }

    suspend fun clear() {
        dao.clear()
    }

    suspend fun count(): Int {
        return dao.count()
    }
}
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
 
package com.keygenqt.demo_contacts.modules.brands.services.data.impl

import com.keygenqt.demo_contacts.base.AppDatabase
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.modules.brands.data.dao.DaoFeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.data.dao.DaoFeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.data.dao.DaoFeedModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel.Companion.getBannersArray
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel.Companion.getBrandsArray
import com.keygenqt.demo_contacts.modules.brands.data.relations.FeedRelation
import kotlinx.coroutines.flow.Flow

interface DataFeedModel {

    val db: AppDatabase
    val preferences: AppPreferences

    private val dao: DaoFeedModel get() = db.daoFeed()
    private val daoBrand: DaoFeedBrandModel get() = db.daoFeedBrandModel()
    private val daoBanner: DaoFeedBannerModel get() = db.daoFeedBannerModel()

    suspend fun insert(vararg models: FeedModel) {
        models.toList().let {
            // save brands
            daoBrand.insertModels(*it.getBrandsArray())
            // save banners
            daoBanner.insertModels(*it.getBannersArray())
            // save model
            dao.insertModels(*models)
        }
    }

    suspend fun clear() {
        daoBrand.clear()
        daoBanner.clear()
        dao.clear()
    }

    fun getFeedRelation(): Flow<FeedRelation?> {
        return dao.getFeedRelation()
    }

    suspend fun count(): Int {
        return dao.count()
    }
}
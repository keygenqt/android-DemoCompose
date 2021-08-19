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

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keygenqt.demo_contacts.modules.brands.data.dao.DaoFeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.data.dao.DaoFeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.data.dao.DaoFeedModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel
import com.keygenqt.demo_contacts.modules.catalog.data.dao.DaoBrandModel
import com.keygenqt.demo_contacts.modules.catalog.data.dao.DaoCategoryModel
import com.keygenqt.demo_contacts.modules.catalog.data.dao.DaoSubCategoryModel
import com.keygenqt.demo_contacts.modules.catalog.data.models.BrandModel
import com.keygenqt.demo_contacts.modules.catalog.data.models.CategoryModel
import com.keygenqt.demo_contacts.modules.catalog.data.models.SubCategoryModel
import com.keygenqt.demo_contacts.modules.favorite.data.dao.DaoFavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel

@Database(
    entities = [
        FavoriteModel::class,
        FeedModel::class,
        FeedBrandModel::class,
        FeedBannerModel::class,
        BrandModel::class,
        CategoryModel::class,
        SubCategoryModel::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoFavorite(): DaoFavoriteModel
    abstract fun daoFeed(): DaoFeedModel
    abstract fun daoFeedBrandModel(): DaoFeedBrandModel
    abstract fun daoFeedBannerModel(): DaoFeedBannerModel
    abstract fun daoBrandModel(): DaoBrandModel
    abstract fun daoCategoryModel(): DaoCategoryModel
    abstract fun daoSubCategoryModel(): DaoSubCategoryModel
}

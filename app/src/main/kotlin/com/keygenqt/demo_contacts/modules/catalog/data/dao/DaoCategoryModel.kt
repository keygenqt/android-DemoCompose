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
 
package com.keygenqt.demo_contacts.modules.catalog.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keygenqt.demo_contacts.modules.catalog.data.models.CategoryModel
import com.keygenqt.demo_contacts.modules.catalog.data.relations.CategoryRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCategoryModel {
    @Query("SELECT * FROM CategoryModel")
    fun pagingSource(): PagingSource<Int, CategoryRelation>

    @Query("SELECT * FROM CategoryModel WHERE id = :id")
    fun getModel(id: Int): Flow<CategoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: CategoryModel)

    @Query("DELETE FROM CategoryModel")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM CategoryModel")
    suspend fun count(): Int
}

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
 
package com.keygenqt.demo_contacts.modules.brands.data.dao

import androidx.room.*
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel
import com.keygenqt.demo_contacts.modules.brands.data.relations.FeedRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFeedModel {

    @Transaction
    @Query("SELECT * FROM FeedModel")
    fun getFeedRelation(): Flow<FeedRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: FeedModel)

    @Query("DELETE FROM FeedModel")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM FeedModel")
    suspend fun count(): Int
}

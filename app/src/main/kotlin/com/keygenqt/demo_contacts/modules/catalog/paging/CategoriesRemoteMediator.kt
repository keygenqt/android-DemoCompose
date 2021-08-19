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
 
package com.keygenqt.demo_contacts.modules.catalog.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.keygenqt.demo_contacts.base.*
import com.keygenqt.demo_contacts.modules.catalog.data.relations.CategoryRelation
import com.keygenqt.demo_contacts.modules.catalog.services.apiService.ApiServiceCatalog
import com.keygenqt.demo_contacts.modules.catalog.services.data.DataServiceCatalog
import com.keygenqt.demo_contacts.utils.ConstantsPaging.CACHE_TIMEOUT
import timber.log.Timber
import kotlin.math.roundToInt

@ExperimentalPagingApi
class CategoriesRemoteMediator(
    private val data: DataServiceCatalog,
    private val apiService: ApiServiceCatalog,
) : RemoteMediator<Int, CategoryRelation>() {

    override suspend fun initialize(): InitializeAction {
        return if (System.currentTimeMillis() - data.preferences.lastUpdateListCategories >= CACHE_TIMEOUT) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryRelation>,
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> (data.countCategoryModel() / state.config.pageSize.toFloat())
                    .roundToInt()
                    .plus(1)
            }

            val response = apiService.getListCatalog(
                page = page ?: 0
            )

            response.success { models ->
                data.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        preferences.lastUpdateListCategories = System.currentTimeMillis()
                        clearCategoryModel()
                    }
                    if (!response.isEndDouble(state.lastItemOrNull()?.owner?.id) || loadType != LoadType.APPEND) {
                        insertCategoryModel(*models.toTypedArray())
                    }
                }
            }.error {
                Timber.e(it)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isError
                        || response.isEmpty
                        || response.isEndDouble(state.lastItemOrNull()?.owner?.id)
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
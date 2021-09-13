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

package com.keygenqt.demo_contacts.modules.favorite.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.keygenqt.demo_contacts.base.*
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.services.apiService.ApiServiceFavorite
import com.keygenqt.demo_contacts.modules.favorite.services.data.DataServiceFavorite
import com.keygenqt.demo_contacts.utils.ConstantsPaging.CACHE_TIMEOUT
import com.keygenqt.response.*
import com.keygenqt.response.extensions.*
import timber.log.Timber
import kotlin.math.roundToInt

@ExperimentalPagingApi
class FavoriteRemoteMediator(
    private val data: DataServiceFavorite,
    private val apiService: ApiServiceFavorite,
    private val onErrorUnknownHost: (Boolean) -> Unit,
) : RemoteMediator<Int, FavoriteModel>() {

    override suspend fun initialize(): InitializeAction {
        return if (System.currentTimeMillis() - data.preferences.lastUpdateListFavorite >= CACHE_TIMEOUT) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FavoriteModel>,
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> (data.count() / state.config.pageSize.toFloat())
                    .roundToInt()
                    .plus(1)
            }

            val response = apiService.getListFavorites(
                page = page ?: 0
            )

            response.success { models ->
                data.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        preferences.lastUpdateListFavorite = System.currentTimeMillis()
                        clear()
                    }
                    if (!response.isEmpty || loadType != LoadType.APPEND) {
                        insert(*models.toTypedArray())
                    }
                }
            }.error {
                Timber.e(it)
            }.done {
                onErrorUnknownHost.invoke(false)
            }.errorUnknownHost {
                onErrorUnknownHost.invoke(true)
            }

            MediatorResult.Success(
                endOfPaginationReached = true
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
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

package com.keygenqt.demo_contacts.modules.favorite.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.keygenqt.demo_contacts.data.models.ProductModel
import com.keygenqt.demo_contacts.modules.favorite.paging.FavoriteRemoteMediator
import com.keygenqt.demo_contacts.modules.favorite.services.ApiServiceFavorite
import com.keygenqt.demo_contacts.modules.favorite.services.DataServiceFavorite
import com.keygenqt.demo_contacts.utils.ConstantsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class FavoriteViewModel @Inject constructor(
    apiService: ApiServiceFavorite,
    private val data: DataServiceFavorite,
) : ViewModel() {

    @ExperimentalPagingApi
    val listFavorite: Flow<PagingData<ProductModel>> = Pager(
        config = PagingConfig(pageSize = ConstantsPaging.PAGE_LIMIT),
        remoteMediator = FavoriteRemoteMediator(data, apiService)
    ) {
        data.pagingListFavorite()
    }.flow
}

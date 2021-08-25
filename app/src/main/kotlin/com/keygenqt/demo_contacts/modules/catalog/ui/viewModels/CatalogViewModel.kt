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
 
package com.keygenqt.demo_contacts.modules.catalog.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.keygenqt.demo_contacts.modules.catalog.data.models.BrandModel
import com.keygenqt.demo_contacts.modules.catalog.data.relations.CategoryRelation
import com.keygenqt.demo_contacts.modules.catalog.paging.BrandsRemoteMediator
import com.keygenqt.demo_contacts.modules.catalog.paging.CategoriesRemoteMediator
import com.keygenqt.demo_contacts.modules.catalog.services.apiService.ApiServiceCatalog
import com.keygenqt.demo_contacts.modules.catalog.services.data.DataServiceCatalog
import com.keygenqt.demo_contacts.utils.ConstantsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val data: DataServiceCatalog,
    apiService: ApiServiceCatalog,
) : ViewModel() {

    private val _errorConnection: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorConnection: StateFlow<Boolean> get() = _errorConnection.asStateFlow()

    @ExperimentalPagingApi
    val listBrands: Flow<PagingData<BrandModel>> = Pager(
        config = PagingConfig(pageSize = ConstantsPaging.PER_PAGE),
        remoteMediator = BrandsRemoteMediator(data, apiService) { status ->
            _errorConnection.value = status
        }
    ) {
        data.pagingListBrandModel()
    }.flow

    @ExperimentalPagingApi
    val listCategories: Flow<PagingData<CategoryRelation>> = Pager(
        config = PagingConfig(pageSize = ConstantsPaging.PER_PAGE),
        remoteMediator = CategoriesRemoteMediator(data, apiService) { status ->
            _errorConnection.value = status
        }
    ) {
        data.pagingListCategoryModel()
    }.flow

}

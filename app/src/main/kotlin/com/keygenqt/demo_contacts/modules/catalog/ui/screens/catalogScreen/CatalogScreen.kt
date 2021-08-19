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
 
package com.keygenqt.demo_contacts.modules.catalog.ui.screens.catalogScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.modules.catalog.data.models.BrandModel
import com.keygenqt.demo_contacts.modules.catalog.data.relations.CategoryRelation
import com.keygenqt.demo_contacts.modules.catalog.ui.events.CatalogEvents
import com.keygenqt.demo_contacts.modules.catalog.ui.viewModels.CatalogViewModel

@ExperimentalPagingApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel,
    onEvent: (CatalogEvents) -> Unit = {},
) {

    val listBrands: LazyPagingItems<BrandModel> = viewModel.listBrands.collectAsLazyPagingItems()
    val listCategories: LazyPagingItems<CategoryRelation> = viewModel.listCategories.collectAsLazyPagingItems()

    CatalogBody(
        onEvent = onEvent,
        listBrands = listBrands,
        listCategories = listCategories,
    )
}

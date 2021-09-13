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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.accompanist.SwipeRefreshList
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.ListenRefresh
import com.keygenqt.demo_contacts.modules._common.ui.compose.Loader
import com.keygenqt.demo_contacts.modules._common.ui.compose.PlugBlock
import com.keygenqt.demo_contacts.modules.catalog.data.relations.CategoryRelation
import com.keygenqt.demo_contacts.modules.catalog.navigation.nav.CatalogNav
import com.keygenqt.demo_contacts.modules.catalog.ui.events.CatalogEvents
import com.keygenqt.modifier.paddingLarge
import com.keygenqt.response.LocalTryExecuteWithResponse
import timber.log.Timber


@Composable
fun CatalogListCategories(
    items: LazyPagingItems<CategoryRelation>,
    onEvent: (CatalogEvents) -> Unit = {},
) {

    val error by LocalTryExecuteWithResponse.current.collectAsState(null)
    LaunchedEffect(error) {
        error?.let {
            Timber.e("------------------- Common error listener")
            Timber.e(it)
            Timber.e("------------------- ")
        }
    }

    LocalBaseViewModel.current.ListenRefresh {
        if (it == CatalogNav.MainNav.CatalogScreen.route) items.refresh()
    }

    SwipeRefreshList(
        modifier = Modifier,
        items = items,
        state = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading),
        contentEmpty = {
            PlugBlock(
                title = stringResource(id = R.string.common_state_empty_title),
                text = stringResource(id = R.string.catalog_state_empty_text_categories),
            )
        },
        contentLoadState = {
            if (it is LoadState.Loading) {
                Loader(Modifier.paddingLarge())
            }
        }
    ) { _, model ->
        CatalogListCategoryItem(model = model)
    }
}
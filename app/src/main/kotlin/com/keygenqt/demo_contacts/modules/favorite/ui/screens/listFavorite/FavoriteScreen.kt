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
 
package com.keygenqt.demo_contacts.modules.favorite.ui.screens.listFavorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.modules._common.ui.compose.ErrorNetworkScreen
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.ui.events.FavoriteEvents
import com.keygenqt.demo_contacts.modules.favorite.ui.viewModels.FavoriteViewModel

@ExperimentalPagingApi
@ExperimentalComposeUiApi
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onEvent: (FavoriteEvents) -> Unit = {},
) {
    val localBaseViewModel = LocalBaseViewModel.current

    val isLogin by localBaseViewModel.isLogin.collectAsState()

    val items: LazyPagingItems<FavoriteModel> = viewModel.listFavorite.collectAsLazyPagingItems()

    FavoriteBody(
        items = items,
        isLogin = isLogin,
        onEvent = onEvent,
    )

    if (viewModel.errorConnection.collectAsState().value) {
        ErrorNetworkScreen(items.loadState.refresh is LoadState.Loading)
    }
}

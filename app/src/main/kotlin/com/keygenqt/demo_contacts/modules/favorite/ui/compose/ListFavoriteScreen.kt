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

package com.keygenqt.demo_contacts.modules.favorite.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.data.models.ProductModel
import com.keygenqt.demo_contacts.modules.common.ui.compose.CommonList
import com.keygenqt.demo_contacts.modules.common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.favorite.ui.events.ListFavoriteEvents
import com.keygenqt.demo_contacts.modules.favorite.ui.viewModels.FavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@Composable
fun ListFavoriteScreen(
    viewModel: FavoriteViewModel,
    onNavigationEvent: (ListFavoriteEvents) -> Unit = {},
) {
    val isShowSnackBar: Boolean by LocalBaseViewModel.current.showSnackBar.collectAsState()
    val items: LazyPagingItems<ProductModel> = viewModel.listFavorite.collectAsLazyPagingItems()

    Box {
        ListFavoriteBody(
            items = items,
            onNavigationEvent = onNavigationEvent,
        )
        if (isShowSnackBar) {
            Snackbar(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(text = stringResource(id = R.string.common_exit))
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ListFavoriteBody(
    items: LazyPagingItems<ProductModel>,
    onNavigationEvent: (ListFavoriteEvents) -> Unit = {},
) {
    val showDialogCreate = remember { mutableStateOf(false) }

    Box {
        MainScaffold(
            icon = null,
            label = stringResource(id = R.string.list_favorite_title),
            contentFloatingActionButton = {
                Icon(Icons.Filled.Add, "Add")
            },
            floatingActionButtonOnClick = {
                showDialogCreate.value = true
            },
        ) {
            CommonList(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                items = items,
                paddingBottom = 24.dp,
                state = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading)
            ) { index, item ->
                ListFavoriteScreenItem(
                    index = index,
                    item = item,
                    onNavigationEvent = onNavigationEvent
                )
            }
        }
    }
}


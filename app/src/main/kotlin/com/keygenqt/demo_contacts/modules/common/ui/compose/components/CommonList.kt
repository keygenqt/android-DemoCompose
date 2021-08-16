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

package com.keygenqt.demo_contacts.modules.common.ui.compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.visible
import timber.log.Timber

@Composable
fun <T : Any> CommonList(
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    paddingBottom: Dp = 0.dp,
    items: LazyPagingItems<T>,
    state: SwipeRefreshState,
    content: @Composable (Int, T) -> Unit,
) {
    // Update after the second click on the active bottom navigator
    val localBaseViewModel = LocalBaseViewModel.current
    val refresh by localBaseViewModel.toggleRefresh.collectAsState()
    LaunchedEffect(refresh) {
        if (refresh) {
            items.refresh()
        }
    }

    SwipeRefresh(
        state = state,
        onRefresh = {
            items.refresh()
        },
        indicator = { st, tr ->
            SwipeRefreshIndicator(
                state = st,
                refreshTriggerDistance = tr,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        if (items.itemCount != 0) {
            LazyColumn(
                contentPadding = PaddingValues(start = padding, top = padding, end = padding, bottom = paddingBottom),
                modifier = Modifier
                    .fillMaxSize()
                    .visible(items.loadState.refresh !is LoadState.Loading)
            ) {
                itemsIndexed(items) { index, item ->
                    item?.let {
                        content.invoke(index, item)
                    }
                }
                items.apply {
                    when {
                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val error = items.loadState.refresh as? LoadState.Error
                            error?.let {
                                item {
                                    Timber.e("Refresh error: $error.error.localizedMessage")
                                }
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val error = items.loadState.refresh as? LoadState.Error
                            error?.let {
                                item {
                                    Timber.e("Append error: $error.error.localizedMessage")
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) { }
        }
    }
    CommonEmpty(
        items.itemCount == 0
                && items.loadState.refresh !is LoadState.Loading && items.loadState.prepend !is LoadState.Loading
    )
    CommonLoading(items.loadState.refresh is LoadState.Loading)
}


@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}
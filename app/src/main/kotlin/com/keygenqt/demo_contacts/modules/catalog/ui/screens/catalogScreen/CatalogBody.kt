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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.catalog.data.models.BrandModel
import com.keygenqt.demo_contacts.modules.catalog.data.relations.CategoryRelation
import com.keygenqt.demo_contacts.modules.catalog.ui.events.CatalogEvents
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.MainScaffold
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun CatalogBody(
    listBrands: LazyPagingItems<BrandModel>,
    listCategories: LazyPagingItems<CategoryRelation>,
    onEvent: (CatalogEvents) -> Unit = {},
) {

    val scope = rememberCoroutineScope()
    val titles = listOf(
        stringResource(id = R.string.catalog_tab_1),
        stringResource(id = R.string.catalog_tab_2)
    )
    val pagerState = rememberPagerState(pageCount = titles.size)

    MainScaffold(
        elevation = 0.dp,
        title = stringResource(id = R.string.catalog_title),
        searchListener = { search ->

        }
    ) {
        Column {
            TabRow(
                backgroundColor = MaterialTheme.colors.primary,
                selectedTabIndex = pagerState.currentPage,
                indicator = @Composable { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    )
                },
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title, color = MaterialTheme.colors.onPrimary) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> CatalogListCategories(
                        onEvent = onEvent,
                        items = listCategories
                    )
                    1 -> CatalogListBrands(
                        onEvent = onEvent,
                        items = listBrands
                    )
                }
            }
        }
    }
}

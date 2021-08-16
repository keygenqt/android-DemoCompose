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
 
package com.keygenqt.demo_contacts.modules.favorite.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.CommonList
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.MainScaffold
import com.keygenqt.demo_contacts.modules.favorite.data.mock.mockFavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.ui.events.FavoriteEvents
import com.keygenqt.demo_contacts.modules.favorite.ui.viewModels.FavoriteViewModel
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalPagingApi
@ExperimentalComposeUiApi
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onNavigationEvent: (FavoriteEvents) -> Unit = {},
) {

    val items: LazyPagingItems<FavoriteModel> = viewModel.listFavorite.collectAsLazyPagingItems()

    FavoriteBody(
        items = items,
        onNavigationEvent = onNavigationEvent,
    )
}

@ExperimentalComposeUiApi
@Composable
fun FavoriteBody(
    items: LazyPagingItems<FavoriteModel>,
    onNavigationEvent: (FavoriteEvents) -> Unit = {},
) {
    val resources = LocalContext.current.resources

    MainScaffold(
        title = stringResource(id = R.string.favorite_title).uppercase(),
        subTitle = resources.getQuantityString(R.plurals.favorite_subtitle, 2, 2)
    ) {
        CommonList(
            modifier = Modifier,
            items = items,
            state = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading
            )
        ) { _, model ->
            ItemList(model = model)
        }
    }
}

@Composable
fun ItemList(
    model: FavoriteModel,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                )
                model.description?.let {
                    Text(
                        text = model.description,
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview("Item List User")
@Preview("Item List User (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewItemUser() {
    MyTheme {
        Surface {
            ItemList(model = mockFavoriteModel())
        }
    }
}

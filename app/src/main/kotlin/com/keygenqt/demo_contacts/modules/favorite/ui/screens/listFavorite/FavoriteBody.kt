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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.accompanist.MainScaffoldSearch
import com.keygenqt.accompanist.SwipeRefreshList
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.ListenRefresh
import com.keygenqt.demo_contacts.modules._common.ui.compose.*
import com.keygenqt.demo_contacts.modules.catalog.navigation.nav.CatalogNav
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.navigation.nav.FavoriteNav
import com.keygenqt.demo_contacts.modules.favorite.ui.events.FavoriteEvents
import com.keygenqt.modifier.paddingLarge
import com.keygenqt.modifier.sizeXSmall
import timber.log.Timber

@ExperimentalComposeUiApi
@Composable
fun FavoriteBody(
    isLogin: Boolean = false,
    items: LazyPagingItems<FavoriteModel>,
    onEvent: (FavoriteEvents) -> Unit = {},
) {
    val resources = LocalContext.current.resources

    MainScaffoldSearch(
        contentTitle = {
            TopBarContentTitle(stringResource(id = R.string.favorite_title).uppercase(), TextAlign.Center)
            if (items.itemCount != 0) {
                Spacer(modifier = Modifier.sizeXSmall())
                TopBarContentSubtitle(resources.getQuantityString(R.plurals.favorite_subtitle, items.itemCount, items.itemCount))
            }
        }
    ) {
        if (isLogin) {

            LocalBaseViewModel.current.ListenRefresh {
                if (it == FavoriteNav.MainNav.FavoriteScreen.route) items.refresh()
            }

            SwipeRefreshList(
                modifier = Modifier,
                items = items,
                state = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading),
                contentEmpty = {
                    EmptyListScreen(
                        text = stringResource(id = R.string.favorite_empty_list),
                        painter = painterResource(id = R.drawable.ic_favorite_placeholder)
                    )
                },
                contentLoadState = {
                    if (it is LoadState.Loading) {
                        Loader(Modifier.paddingLarge())
                    }
                }
            ) { _, model ->
                FavoriteItemList(
                    model = model,
                    onEvent = onEvent,
                )
            }
        } else {
            GuestListScreen(
                text = stringResource(id = R.string.favorite_guest_list),
                painter = painterResource(id = R.drawable.ic_favorite_placeholder),
                onSignIn = { onEvent(FavoriteEvents.NavigateToSignIn) }
            )
        }
    }
}
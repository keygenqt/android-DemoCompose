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

package com.keygenqt.demo_contacts.modules.brands.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.ListenRefresh
import com.keygenqt.demo_contacts.modules.brands.data.mock.mockFeedModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.data.relations.FeedRelation
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents
import com.keygenqt.demo_contacts.modules.brands.ui.viewModels.BrandsViewModel
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.CommonLoading
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.MainScaffold
import com.keygenqt.demo_contacts.modules.common.ui.compose.screens.EmptyListScreen
import com.keygenqt.demo_contacts.modules.common.ui.compose.screens.LoadingScreen
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun BrandsScreen(
    viewModel: BrandsViewModel,
    onEvent: (BrandsEvents) -> Unit = {},
) {
    val feed by viewModel.getFeed().collectAsState(null)
    val commonError: String? by viewModel.commonError.collectAsState()
    val loading: Boolean by viewModel.loading.collectAsState()

    BrandsBody(
        feed = feed,
        onEvent = onEvent,
        loading = loading,
        commonError = commonError,
    )
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun BrandsBody(
    feed: FeedRelation?,
    commonError: String? = null,
    loading: Boolean = true,
    onEvent: (BrandsEvents) -> Unit = {},
) {
    MainScaffold(
        title = stringResource(id = R.string.app_name),
        searchListener = { search ->

        }
    ) {

        LocalBaseViewModel.current.ListenRefresh {
            onEvent(BrandsEvents.RefreshFeed)
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(loading),
            onRefresh = { onEvent(BrandsEvents.RefreshFeed) },
            indicator = { st, tr ->
                SwipeRefreshIndicator(
                    state = st,
                    refreshTriggerDistance = tr,
                    contentColor = MaterialTheme.colors.onPrimary,
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            feed?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        BrandsBodyBanners(feed.banners)
                    }
                }
            } ?: run {
                if (!loading) {
                    EmptyListScreen(
                        text = stringResource(id = R.string.brands_empty_feed),
                        painter = painterResource(id = R.drawable.ic_common_not_found)
                    )
                } else {
                    LoadingScreen(loading)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun BrandsBodyBanners(
    banners: List<FeedBannerModel> = listOf(),
    onEvent: (BrandsEvents) -> Unit = {},
) {
    if (banners.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = banners.size)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            HorizontalPager(
                state = pagerState,
            ) { page ->
                BrandsBodyBanner(banners[page])
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}


@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun BrandsBodyBanner(
    banner: FeedBannerModel,
    onEvent: (BrandsEvents) -> Unit = {},
) {

    val context = LocalContext.current
    var loading: Boolean by remember { mutableStateOf(false) }

    Box {
        CommonLoading(
            visibility = loading,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
        )
        Image(
            contentScale = ContentScale.Crop,
            painter = rememberImagePainter(banner.image.url, onExecute = { previous, current ->
                loading = current.state is ImagePainter.State.Empty
                ImagePainter.ExecuteCallback.Default.invoke(previous, current)
            }),
            contentDescription = banner.image.imageType,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        if (!loading && banner.expandData.brandCode.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = {
                            Toast.makeText(context, R.string.common_coming_soon, Toast.LENGTH_SHORT).show()
                        }
                    )
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewBrandsBody() {
    MyTheme {
        Surface {
            BrandsBody(feed = mockFeedModel())
        }
    }
}

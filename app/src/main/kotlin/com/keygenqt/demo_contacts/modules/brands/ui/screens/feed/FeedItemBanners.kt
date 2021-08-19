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
 
package com.keygenqt.demo_contacts.modules.brands.ui.screens.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun FeedItemBanners(
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
                FeedItemBanner(banners[page])
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
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

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.CommonLoading

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun FeedItemBanner(
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
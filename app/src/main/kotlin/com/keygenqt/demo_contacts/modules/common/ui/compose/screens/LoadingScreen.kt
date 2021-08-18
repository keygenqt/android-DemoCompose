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

package com.keygenqt.demo_contacts.modules.common.ui.compose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.keygenqt.demo_contacts.R

@Composable
fun LoadingScreen(
    visibility: Boolean,
) {
    Box {
        when (remember { (0..1).random() }) {
            0 -> CoastLoadingScreen(visibility = visibility)
            1 -> JapanLoadingScreen(visibility = visibility)
        }
    }
}

@Composable
fun BoxScope.CoastLoadingScreen(
    visibility: Boolean,
) {
    val coastComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lost_coast),
    )

    val progress by animateLottieCompositionAsState(
        composition = coastComposition,
        iterations = LottieConstants.IterateForever
    )

    if (visibility) {
        LottieAnimation(
            composition = coastComposition,
            progress = progress,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(30.dp)
        )
    }
}

@Composable
fun BoxScope.JapanLoadingScreen(
    visibility: Boolean,
) {
    val japanComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.japan_scene),
    )

    val progress by animateLottieCompositionAsState(
        composition = japanComposition,
        iterations = LottieConstants.IterateForever
    )

    if (visibility) {
        Box {
            LottieAnimation(
                composition = japanComposition,
                progress = progress,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(30.dp)
            )
        }
    }
}
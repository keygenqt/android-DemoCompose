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
 
package com.keygenqt.demo_contacts.modules._common.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalMaterialApi
@Composable
fun BottomSheetScaffoldInfo(
    isShow: Boolean = false,
    content: @Composable ColumnScope.(BottomSheetScaffoldState) -> Unit,
) {
    rememberSystemUiController().setStatusBarColor(
        color = if (isShow) Color.Black.copy(alpha = 0.7f) else MaterialTheme.colors.primaryVariant
    )

    if (isShow) {
        val state = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        BottomSheetScaffold(
            sheetGesturesEnabled = false,
            sheetBackgroundColor = Color.Transparent,
            backgroundColor = Color.Black.copy(alpha = 0.7f),
            scaffoldState = state,
            sheetPeekHeight = 0.dp,
            sheetElevation = 0.dp,
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 350.dp)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colors.background)
                            .align(Alignment.BottomStart)
                    ) {
                        content.invoke(this, state)
                    }
                }
            }
        ) {}

        LaunchedEffect(isShow) {
            if (state.bottomSheetState.isCollapsed) {
                state.bottomSheetState.expand()
            } else {
                state.bottomSheetState.collapse()
            }
        }
    }
}
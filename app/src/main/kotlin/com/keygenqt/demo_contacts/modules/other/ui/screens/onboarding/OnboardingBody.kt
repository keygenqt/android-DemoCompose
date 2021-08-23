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
 
package com.keygenqt.demo_contacts.modules.other.ui.screens.onboarding

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.keygenqt.demo_contacts.modules._common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.other.ui.events.StartEvents
import com.keygenqt.demo_contacts.theme.MyTheme
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun OnboardingBody(
    onEvent: (StartEvents) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = 2)

    MainScaffold {
        HorizontalPager(
            dragEnabled = false,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> OnboardingItem1 {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
                1 -> OnboardingItem2 {
                    onEvent(StartEvents.NavigateToBrands)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StartBodyPreview() {
    MyTheme {
        Surface {
            OnboardingBody()
        }
    }
}
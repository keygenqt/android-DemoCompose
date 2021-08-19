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
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.MainScaffold
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
    val pagerState = rememberPagerState(pageCount = 4)

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
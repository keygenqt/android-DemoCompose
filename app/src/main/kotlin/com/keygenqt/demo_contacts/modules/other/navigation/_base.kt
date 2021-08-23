package com.keygenqt.demo_contacts.modules.other.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.ui.viewModels.MainViewModel

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.otherNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    onboardingNavGraph(
        navActions = navActions,
        baseViewModel = baseViewModel,
    )
    authNavGraph(
        navActions = navActions,
        baseViewModel = baseViewModel,
    )
}
package com.keygenqt.demo_contacts.modules.other.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.navigation.NavScreen
import com.keygenqt.demo_contacts.modules._common.ui.viewModels.MainViewModel
import com.keygenqt.demo_contacts.modules.other.ui.events.StartEvents
import com.keygenqt.demo_contacts.modules.other.ui.screens.onboarding.OnboardingScreen

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
fun NavGraphBuilder.onboardingNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.OnboardingScreen.route) {
        OnboardingScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is StartEvents.NavigateToBrands -> {
                    baseViewModel.startPageCompleted()
                    navActions.navigateToBrands.invoke()
                }
            }
        }
    }
}
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
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents
import com.keygenqt.demo_contacts.modules.brands.ui.screens.feed.FeedScreen
import com.keygenqt.demo_contacts.modules.brands.ui.viewModels.BrandsViewModel
import com.keygenqt.demo_contacts.modules.other.ui.events.SignInEvents
import com.keygenqt.demo_contacts.modules.other.ui.screens.signIn.SignInScreen
import com.keygenqt.demo_contacts.modules.other.ui.viewModels.OtherViewModel

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
fun NavGraphBuilder.authNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.SignInScreen.route) {
        val viewModel: OtherViewModel = hiltViewModel()
        SignInScreen(viewModel = viewModel) { event ->
            when (event) {
                is SignInEvents.NavigateBack -> navActions.navigateToUp.invoke()
                is SignInEvents.SignIn -> viewModel.signIn(
                    event.login,
                    event.passw
                ) { accessToken, refreshToken ->
                    baseViewModel.startUser(accessToken, refreshToken)
                    navActions.navigateToUp.invoke()
                }
            }
        }
    }
}
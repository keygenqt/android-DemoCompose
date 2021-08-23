package com.keygenqt.demo_contacts.modules.cart.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.navigation.NavScreen
import com.keygenqt.demo_contacts.modules._common.ui.viewModels.MainViewModel
import com.keygenqt.demo_contacts.modules.cart.ui.events.CartEvents
import com.keygenqt.demo_contacts.modules.cart.ui.screens.CartScreen

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.cartNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.CartScreen.route) {
        CartScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is CartEvents.NavigateBack -> navActions.navigateToUp.invoke()
            }
        }
    }
}
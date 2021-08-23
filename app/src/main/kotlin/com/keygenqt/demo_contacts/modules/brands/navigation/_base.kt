package com.keygenqt.demo_contacts.modules.brands.navigation

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

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.brandsNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.BrandsScreen.route) {
        val viewModel: BrandsViewModel = hiltViewModel()
        FeedScreen(viewModel = viewModel) { event ->
            when (event) {
                is BrandsEvents.RefreshFeed -> viewModel.updateFeed()
            }
        }
    }
}
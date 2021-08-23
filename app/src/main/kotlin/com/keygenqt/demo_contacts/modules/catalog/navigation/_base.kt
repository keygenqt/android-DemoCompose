package com.keygenqt.demo_contacts.modules.catalog.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.navigation.NavScreen
import com.keygenqt.demo_contacts.modules._common.ui.viewModels.MainViewModel
import com.keygenqt.demo_contacts.modules.catalog.ui.events.CatalogEvents
import com.keygenqt.demo_contacts.modules.catalog.ui.screens.catalogScreen.CatalogScreen

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.catalogNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.CatalogScreen.route) {
        CatalogScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is CatalogEvents.NavigateBack -> navActions.navigateToUp.invoke()
            }
        }
    }
}
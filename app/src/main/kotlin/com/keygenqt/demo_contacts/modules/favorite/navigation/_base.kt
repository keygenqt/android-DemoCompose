package com.keygenqt.demo_contacts.modules.favorite.navigation

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
import com.keygenqt.demo_contacts.modules.favorite.ui.events.FavoriteEvents
import com.keygenqt.demo_contacts.modules.favorite.ui.screens.listFavorite.FavoriteScreen

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.favoriteNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.FavoriteScreen.route) {
        FavoriteScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is FavoriteEvents.NavigateToSignIn -> navActions.navigateToSignIn.invoke()
                is FavoriteEvents.NavigateBack -> navActions.navigateToUp.invoke()
            }
        }
    }
}
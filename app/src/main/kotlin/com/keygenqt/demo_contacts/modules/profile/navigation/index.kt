package com.keygenqt.demo_contacts.modules.profile.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.navigation.NavScreen
import com.keygenqt.demo_contacts.modules._common.ui.viewModels.MainViewModel
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import com.keygenqt.demo_contacts.modules.profile.ui.screens.profileScreen.ProfileScreen

@ExperimentalComposeUiApi
fun NavGraphBuilder.indexNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    composable(NavScreen.ProfileScreen.route) {
        ProfileScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is ProfileEvents.NavigateToContactSettings -> navActions.navigateToContactSettings.invoke()
                is ProfileEvents.NavigateToSignIn -> navActions.navigateToSignIn.invoke()
                is ProfileEvents.NavigateLogout -> baseViewModel.logout()
            }
        }
    }
}
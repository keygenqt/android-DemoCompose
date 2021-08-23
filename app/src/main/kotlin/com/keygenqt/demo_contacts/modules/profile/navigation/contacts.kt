package com.keygenqt.demo_contacts.modules.profile.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.navigation.NavScreen
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangeEmailEvents
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangePhoneEvents
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactSettingsEvents
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangeEmailScreen.ContactChangeEmailScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangePhoneScreen.ContactChangePhoneScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactSettingsScreen.ContactSettingsScreen

@ExperimentalComposeUiApi
fun NavGraphBuilder.contactsNavGraph(
    navActions: NavActions,
) {
    composable(NavScreen.ContactSettingsScreen.route) {
        ContactSettingsScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is ContactSettingsEvents.NavigateBack -> navActions.navigateToUp()
                is ContactSettingsEvents.NavigateToContactChangeEmail -> navActions.navigateToContactChangeEmail()
                is ContactSettingsEvents.NavigateToContactChangePhone -> navActions.navigateToContactChangePhone()
            }
        }
    }
    composable(NavScreen.ContactChangeEmailScreen.route) {
        ContactChangeEmailScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is ContactChangeEmailEvents.NavigateBack -> navActions.navigateToUp()
            }
        }
    }
    composable(NavScreen.ContactChangePhoneScreen.route) {
        ContactChangePhoneScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is ContactChangePhoneEvents.NavigateBack -> navActions.navigateToUp()
            }
        }
    }
}
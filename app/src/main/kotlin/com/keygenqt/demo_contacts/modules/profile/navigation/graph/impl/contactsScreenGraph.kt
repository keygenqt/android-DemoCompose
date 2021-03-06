/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.keygenqt.demo_contacts.modules.profile.navigation.graph.impl

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules.profile.navigation.nav.ProfileNav
import com.keygenqt.demo_contacts.modules.profile.ui.events.*
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangeEmail.ContactChangeEmailScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangeEmailCode.ContactChangeEmailCodeScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangePhone.ContactChangePhoneScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangePhoneCode.ContactChangePhoneCodeScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactSettings.ContactSettingsScreen
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileChangeContactsViewModel
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileContactsViewModel

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.contactsScreenGraph(
    navActions: NavActions,
) {
    composable(
        route = ProfileNav.ContactsNav.ContactSettingsScreen.routeWithArgument,
        arguments = listOf(
            navArgument(ProfileNav.ContactsNav.ContactSettingsScreen.argument0) {
                type = NavType.StringType; nullable = true
            },
            navArgument(ProfileNav.ContactsNav.ContactSettingsScreen.argument1) {
                type = NavType.StringType; nullable = true
            }
        )
    ) { backStackEntry ->
        val viewModel: ProfileContactsViewModel = hiltViewModel()
        ContactSettingsScreen(
            argumentUpdatedEmail = backStackEntry.arguments?.getString(ProfileNav.ContactsNav.ContactSettingsScreen.argument0),
            argumentUpdatedPhone = backStackEntry.arguments?.getString(ProfileNav.ContactsNav.ContactSettingsScreen.argument1),
            viewModel = viewModel,
            onEvent = { event: ContactSettingsEvents ->
                when (event) {
                    is ContactSettingsEvents.NavigateBack -> navActions.navigateToUp()
                    is ContactSettingsEvents.NavigateToContactChangeEmail -> navActions.navigateToContactChangeEmail()
                    is ContactSettingsEvents.NavigateToContactChangePhone -> navActions.navigateToContactChangePhone()
                    is ContactSettingsEvents.UpdateUserContacts -> viewModel.userUpdateContacts()
                    is ContactSettingsEvents.UpdateStatusSmall -> viewModel.updateStatusSmall(
                        event.statusEmail,
                        event.statusPhone
                    )
                    is ContactSettingsEvents.UpdateStatusFull -> viewModel.updateStatusFull(
                        event.statusEmail,
                        event.statusPhone
                    )
                }
            },
        )
    }
    composable(ProfileNav.ContactsNav.ContactChangeEmailScreen.route) {
        val viewModel: ProfileChangeContactsViewModel = hiltViewModel()
        ContactChangeEmailScreen(viewModel = viewModel) { event ->
            when (event) {
                is ContactChangeEmailEvents.ContactChangeEmail -> viewModel.changeEmailOrPhone(event.email) {
                    navActions.navigateToContactChangeCodeEmail(event.email)
                }
                is ContactChangeEmailEvents.NavigateBack -> navActions.navigateToUp()
            }
        }
    }
    composable(
        route = ProfileNav.ContactsNav.ContactChangeEmailCodeScreen.routeWithArgument,
        arguments = listOf(navArgument(ProfileNav.ContactsNav.ContactChangeEmailCodeScreen.argument0) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        backStackEntry.arguments?.let {
            val viewModel: ProfileChangeContactsViewModel = hiltViewModel()
            val email = it.getString(ProfileNav.ContactsNav.ContactChangeEmailCodeScreen.argument0)!!
            viewModel.runTimer()
            ContactChangeEmailCodeScreen(
                email = email,
                viewModel = viewModel
            ) { event ->
                when (event) {
                    is ContactChangeEmailCodeEvents.ContactChangeEmailCode -> viewModel.checkEmailOrPhone(email,
                        event.code) {
                        navActions.navigateToContactSettingsUpdatedEmail(email)
                    }
                    is ContactChangeEmailCodeEvents.ContactChangeEmailCodeRefresh -> viewModel.changeEmailOrPhone(email) {
                        viewModel.runTimer()
                    }
                    is ContactChangeEmailCodeEvents.NavigateBack -> navActions.navigateToUp()
                }
            }
        }
    }
    composable(ProfileNav.ContactsNav.ContactChangePhoneScreen.route) {
        val viewModel: ProfileChangeContactsViewModel = hiltViewModel()
        ContactChangePhoneScreen(viewModel = viewModel) { event ->
            when (event) {
                is ContactChangePhoneEvents.ContactChangePhone -> viewModel.changeEmailOrPhone(event.phone) {
                    navActions.navigateToContactChangeCodePhone(event.phone)
                }
                is ContactChangePhoneEvents.NavigateBack -> navActions.navigateToUp()
            }
        }
    }
    composable(
        route = ProfileNav.ContactsNav.ContactChangePhoneCodeScreen.routeWithArgument,
        arguments = listOf(navArgument(ProfileNav.ContactsNav.ContactChangePhoneCodeScreen.argument0) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        backStackEntry.arguments?.let {
            val viewModel: ProfileChangeContactsViewModel = hiltViewModel()
            val phone = it.getString(ProfileNav.ContactsNav.ContactChangePhoneCodeScreen.argument0)!!
            viewModel.runTimer()
            ContactChangePhoneCodeScreen(
                phone = phone,
                viewModel = viewModel
            ) { event ->
                when (event) {
                    is ContactChangePhoneCodeEvents.ContactChangePhoneCode -> viewModel.checkEmailOrPhone(phone,
                        event.code) {
                        navActions.navigateToContactSettingsUpdatedPhone(phone)
                    }
                    is ContactChangePhoneCodeEvents.ContactChangePhoneCodeRefresh -> viewModel.changeEmailOrPhone(phone) {
                        viewModel.runTimer()
                    }
                    is ContactChangePhoneCodeEvents.NavigateBack -> navActions.navigateToUp()
                }
            }
        }
    }
}
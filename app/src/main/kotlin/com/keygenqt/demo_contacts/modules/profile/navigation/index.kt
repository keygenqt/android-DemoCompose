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
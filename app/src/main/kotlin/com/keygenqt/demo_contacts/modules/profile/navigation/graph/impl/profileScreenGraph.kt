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
import androidx.navigation.compose.composable
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules.profile.navigation.nav.ProfileNav
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import com.keygenqt.demo_contacts.modules.profile.ui.screens.profileScreen.ProfileScreen
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileViewModel

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.profileScreenGraph(
    navActions: NavActions,
) {
    composable(ProfileNav.ProfileNav.ProfileScreen.route) {
        val baseViewModel = LocalBaseViewModel.current
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is ProfileEvents.UpdateUser -> viewModel.userUpdate()
                is ProfileEvents.NavigateToContactSettings -> navActions.navigateToContactSettings()
                is ProfileEvents.NavigateToSignIn -> navActions.navigateToSignIn()
                is ProfileEvents.NavigateLogout -> baseViewModel.logout()
            }
        }
    }
}
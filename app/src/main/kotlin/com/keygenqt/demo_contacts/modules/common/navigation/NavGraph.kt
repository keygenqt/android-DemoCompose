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

package com.keygenqt.demo_contacts.modules.common.navigation

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.AddChangeRouteListener
import com.keygenqt.demo_contacts.modules.other.ui.compose.LoginScreen
import com.keygenqt.demo_contacts.modules.other.ui.compose.WelcomeScreen
import com.keygenqt.demo_contacts.modules.other.ui.events.LoginEvents
import com.keygenqt.demo_contacts.modules.other.ui.events.WelcomeEvents
import com.keygenqt.demo_contacts.modules.other.ui.viewModels.OtherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun GuestNavGraph(navController: NavHostController) {

    val context = LocalContext.current

    val localBaseViewModel = LocalBaseViewModel.current

    navController.AddChangeRouteListener()

    val navActions = remember(navController) {
        NavActions(navController)
    }

    val viewModel: OtherViewModel = hiltViewModel()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Welcome.route) {
            composable(NavScreen.Welcome.route) {
                WelcomeScreen { event ->
                    when (event) {
                        is WelcomeEvents.ToMainScreen -> navActions.navigateToLogin.invoke()
                    }
                }
            }
            composable(NavScreen.Login.route) {
                LoginScreen(viewModel) { event ->
                    when (event) {
                        is LoginEvents.NavigateBack -> navActions.upPress.invoke()
                    }
                }
            }
        }
    }
}



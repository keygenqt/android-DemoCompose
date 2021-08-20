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
 
package com.keygenqt.demo_contacts.modules._common.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.AddChangeRouteListener
import com.keygenqt.demo_contacts.modules._common.navigation.HomeTab.Companion.findByRoute
import com.keygenqt.demo_contacts.modules._common.ui.compose.BottomBar
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents
import com.keygenqt.demo_contacts.modules.brands.ui.screens.feed.FeedScreen
import com.keygenqt.demo_contacts.modules.brands.ui.viewModels.BrandsViewModel
import com.keygenqt.demo_contacts.modules.cart.ui.events.CartEvents
import com.keygenqt.demo_contacts.modules.cart.ui.screens.CartScreen
import com.keygenqt.demo_contacts.modules.catalog.ui.events.CatalogEvents
import com.keygenqt.demo_contacts.modules.catalog.ui.screens.catalogScreen.CatalogScreen
import com.keygenqt.demo_contacts.modules.favorite.ui.events.FavoriteEvents
import com.keygenqt.demo_contacts.modules.favorite.ui.screens.listFavorite.FavoriteScreen
import com.keygenqt.demo_contacts.modules.other.ui.events.SignInEvents
import com.keygenqt.demo_contacts.modules.other.ui.events.StartEvents
import com.keygenqt.demo_contacts.modules.other.ui.screens.onboarding.OnboardingScreen
import com.keygenqt.demo_contacts.modules.other.ui.screens.signIn.SignInScreen
import com.keygenqt.demo_contacts.modules.other.ui.viewModels.OtherViewModel
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactSettingsEvents
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import com.keygenqt.demo_contacts.modules.profile.ui.screens.contactSettingsScreen.ContactSettingsScreen
import com.keygenqt.demo_contacts.modules.profile.ui.screens.profileScreen.ProfileScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun GuestNavGraph(navController: NavHostController) {

    val localBaseViewModel = LocalBaseViewModel.current

    navController.AddChangeRouteListener()

    val navActions = remember(navController) {
        NavActions(navController)
    }

    ProvideWindowInsets {

        val scaffoldState = rememberScaffoldState()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: NavScreen.BrandsScreen.route

        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = currentRoute.findByRoute()?.let { homeTab ->
                {
                    BottomBar(
                        currentRoute = homeTab,
                        navActions = navActions
                    ) {
                        localBaseViewModel.listRefresh()
                    }
                }
            } ?: run { {} },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                NavHost(navController = navController, startDestination = localBaseViewModel.getStartRoute()) {
                    composable(NavScreen.StartScreen.route) {
                        OnboardingScreen(viewModel = hiltViewModel()) { event ->
                            when (event) {
                                is StartEvents.NavigateToBrands -> {
                                    localBaseViewModel.startPageCompleted()
                                    navActions.navigateToBrands.invoke()
                                }
                            }
                        }
                    }
                    composable(NavScreen.BrandsScreen.route) {
                        val viewModel: BrandsViewModel = hiltViewModel()
                        FeedScreen(viewModel = viewModel) { event ->
                            when (event) {
                                is BrandsEvents.RefreshFeed -> viewModel.updateFeed()
                            }
                        }
                    }
                    composable(NavScreen.CatalogScreen.route) {
                        CatalogScreen(viewModel = hiltViewModel()) { event ->
                            when (event) {
                                is CatalogEvents.NavigateBack -> navActions.navigateToUp.invoke()
                            }
                        }
                    }
                    composable(NavScreen.ProfileScreen.route) {
                        ProfileScreen(viewModel = hiltViewModel()) { event ->
                            when (event) {
                                is ProfileEvents.NavigateToContactSettings -> navActions.navigateToContactSettings.invoke()
                                is ProfileEvents.NavigateToSignIn -> navActions.navigateToSignIn.invoke()
                                is ProfileEvents.NavigateLogout -> localBaseViewModel.logout()
                            }
                        }
                    }
                    composable(NavScreen.FavoriteScreen.route) {
                        FavoriteScreen(viewModel = hiltViewModel()) { event ->
                            when (event) {
                                is FavoriteEvents.NavigateToSignIn -> navActions.navigateToSignIn.invoke()
                                is FavoriteEvents.NavigateBack -> navActions.navigateToUp.invoke()
                            }
                        }
                    }
                    composable(NavScreen.CartScreen.route) {
                        CartScreen(viewModel = hiltViewModel()) { event ->
                            when (event) {
                                is CartEvents.NavigateBack -> navActions.navigateToUp.invoke()
                            }
                        }
                    }
                    composable(NavScreen.SignInScreen.route) {
                        val viewModel: OtherViewModel = hiltViewModel()
                        SignInScreen(viewModel = viewModel) { event ->
                            when (event) {
                                is SignInEvents.NavigateBack -> navActions.navigateToUp.invoke()
                                is SignInEvents.SignIn -> viewModel.signIn(
                                    event.login,
                                    event.passw
                                ) { accessToken, refreshToken ->
                                    localBaseViewModel.startUser(accessToken, refreshToken)
                                    navActions.navigateToUp.invoke()
                                }
                            }
                        }
                    }
                    composable(NavScreen.ContactSettingsScreen.route) {
                        ContactSettingsScreen(viewModel = hiltViewModel()) { event ->
                            when (event) {
                                is ContactSettingsEvents.NavigateBack -> navActions.navigateToUp.invoke()
                            }
                        }
                    }
                }
            }
        }
    }
}



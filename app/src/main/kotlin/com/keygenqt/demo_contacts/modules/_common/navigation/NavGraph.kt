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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.AddChangeRouteListener
import com.keygenqt.demo_contacts.modules._common.navigation.HomeTab.Companion.findByRoute
import com.keygenqt.demo_contacts.modules._common.ui.compose.BottomBar
import com.keygenqt.demo_contacts.modules.brands.navigation.graph.brandsNavGraph
import com.keygenqt.demo_contacts.modules.brands.navigation.nav.BrandsNav
import com.keygenqt.demo_contacts.modules.cart.navigation.graph.cartNavGraph
import com.keygenqt.demo_contacts.modules.catalog.navigation.graph.catalogNavGraph
import com.keygenqt.demo_contacts.modules.favorite.navigation.graph.favoriteNavGraph
import com.keygenqt.demo_contacts.modules.other.navigation.graph.otherNavGraph
import com.keygenqt.demo_contacts.modules.profile.navigation.graph.profileNavGraph


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun NavGraph(navController: NavHostController) {

    navController.AddChangeRouteListener()

    val navActions = remember(navController) {
        NavActions(navController)
    }

    ProvideWindowInsets {

        val scaffoldState = rememberScaffoldState()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: BrandsNav.MainNav.FeedScreen.route

        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = currentRoute.findByRoute()?.let { homeTab ->
                {
                    BottomBar(
                        currentRoute = homeTab,
                        navActions = navActions
                    )
                }
            } ?: run { {} },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                NavHost(navController = navController, startDestination = LocalBaseViewModel.current.getStartRoute()) {
                    brandsNavGraph(
                        navActions = navActions,
                    )
                    cartNavGraph(
                        navActions = navActions,
                    )
                    catalogNavGraph(
                        navActions = navActions,
                    )
                    favoriteNavGraph(
                        navActions = navActions,
                    )
                    otherNavGraph(
                        navActions = navActions,
                    )
                    profileNavGraph(
                        navActions = navActions,
                    )
                }
            }
        }
    }
}



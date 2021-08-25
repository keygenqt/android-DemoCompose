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
 
package com.keygenqt.demo_contacts.modules.catalog.navigation.graph.impl

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules.catalog.navigation.nav.CatalogNav
import com.keygenqt.demo_contacts.modules.catalog.ui.events.CatalogEvents
import com.keygenqt.demo_contacts.modules.catalog.ui.screens.catalogScreen.CatalogScreen
import com.keygenqt.demo_contacts.modules.catalog.ui.viewModels.CatalogViewModel

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
fun NavGraphBuilder.catalogScreenGraph(
    navActions: NavActions,
) {
    composable(CatalogNav.MainNav.CatalogScreen.route) {
        val viewModel: CatalogViewModel = hiltViewModel()
        CatalogScreen(viewModel = viewModel) { event ->
            when (event) {
                is CatalogEvents.NavigateBack -> navActions.navigateToUp.invoke()
            }
        }
    }
}
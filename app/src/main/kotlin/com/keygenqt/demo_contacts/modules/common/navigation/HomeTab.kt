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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeTab(
    val route: String,
    val icon: ImageVector,
) {

    BRANDS(NavScreen.BrandsScreen.route, Icons.Filled.Dashboard),
    CATALOG(NavScreen.CatalogScreen.route, Icons.Filled.ViewList),
    PROFILE(NavScreen.ProfileScreen.route, Icons.Filled.CreditCard),
    FAVORITE(NavScreen.FavoriteScreen.route, Icons.Filled.Favorite),
    CART(NavScreen.CartScreen.route, Icons.Filled.ShoppingCart);

    companion object {
        fun String.findByRoute(): HomeTab {
            return when (this) {
                BRANDS.route -> BRANDS
                CATALOG.route -> CATALOG
                PROFILE.route -> PROFILE
                FAVORITE.route -> FAVORITE
                CART.route -> CART
                else -> BRANDS
            }
        }
    }
}
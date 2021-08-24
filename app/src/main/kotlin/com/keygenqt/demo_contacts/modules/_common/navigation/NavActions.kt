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

import androidx.navigation.NavHostController
import com.keygenqt.demo_contacts.modules.brands.navigation.actions.BrandsNavActions
import com.keygenqt.demo_contacts.modules.cart.navigation.actions.CartNavActions
import com.keygenqt.demo_contacts.modules.catalog.navigation.actions.CatalogNavActions
import com.keygenqt.demo_contacts.modules.favorite.navigation.actions.FavoriteNavActions
import com.keygenqt.demo_contacts.modules.other.navigation.actions.OtherNavActions
import com.keygenqt.demo_contacts.modules.profile.navigation.actions.ProfileNavActions

class NavActions(
    override val controller: NavHostController,
) : BrandsNavActions,
    CartNavActions,
    CatalogNavActions,
    FavoriteNavActions,
    OtherNavActions,
    ProfileNavActions {

    val navigateToUp: () -> Unit = {
        controller.navigateUp()
    }
}
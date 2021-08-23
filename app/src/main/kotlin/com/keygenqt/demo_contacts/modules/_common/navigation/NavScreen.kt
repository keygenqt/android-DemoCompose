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

sealed class NavScreen(val route: String) {
    // brands
    object BrandsScreen : NavScreen("BrandsScreen")

    // cart
    object CartScreen : NavScreen("CartScreen")

    // catalog
    object CatalogScreen : NavScreen("CatalogScreen")

    // favorite
    object FavoriteScreen : NavScreen("FavoriteScreen")

    // other
    object OnboardingScreen : NavScreen("OnboardingScreen")
    object SignInScreen : NavScreen("SignInScreen")

    // profile
    object ProfileScreen : NavScreen("ProfileScreen")
    object ContactSettingsScreen : NavScreen("ContactSettingsScreen")
    object ContactChangeEmailScreen : NavScreen("ContactChangeEmailScreen")
    object ContactChangePhoneScreen : NavScreen("ContactChangePhoneScreen")
}
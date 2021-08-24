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
 
package com.keygenqt.demo_contacts.modules.profile.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.demo_contacts.modules.profile.navigation.nav.ProfileNav

interface ProfileScreenActions {

    val controller: NavHostController

    fun navigateToContactSettings() {
        ProfileNav.ContactsNav.ContactSettingsScreen.apply {
            controller.navigate(routeWithArgument)
        }
    }

    fun navigateToContactSettingsUpdatedEmail(email: String) {
        ProfileNav.ContactsNav.ContactSettingsScreen.apply {
            controller.navigate(getRoute(
                argument0 = email,
                argument1 = null
            )) {
                popUpTo(routeWithArgument) { inclusive = true }
            }
        }
    }

    fun navigateToContactSettingsUpdatedPhone(phone: String) {
        ProfileNav.ContactsNav.ContactSettingsScreen.apply {
            controller.navigate(getRoute(
                argument0 = null,
                argument1 = phone
            )) {
                popUpTo(routeWithArgument) { inclusive = true }
            }
        }
    }

    fun navigateToContactChangeEmail() {
        controller.navigate(ProfileNav.ContactsNav.ContactChangeEmailScreen.route)
    }

    fun navigateToContactChangeCodeEmail(email: String) {
        ProfileNav.ContactsNav.ContactChangeEmailCodeScreen.apply {
            controller.navigate(getRoute(email))
        }
    }
}
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

package com.keygenqt.demo_contacts.modules.profile.navigation.nav.impl

import com.keygenqt.routing.NavScreen
import com.keygenqt.routing.NavScreenWithArgument
import com.keygenqt.routing.NavScreenWithArgument2


object ContactsNavScreen {
    val ContactSettingsScreen = object : NavScreenWithArgument2 {
        override val routeWithArgument: String = "ContactSettingsScreen?email={email}&phone={phone}"
        override val argument0: String = "email"
        override val argument1: String = "phone"
    }
    val ContactChangeEmailScreen = object : NavScreen {
        override val route: String = "ContactChangeEmailScreen"
    }
    val ContactChangeEmailCodeScreen = object : NavScreenWithArgument {
        override val routeWithArgument: String = "ContactChangeEmailCodeScreen/{email}"
        override val argument0: String = "email"
    }
    val ContactChangePhoneScreen = object : NavScreen {
        override val route: String = "ContactChangePhoneScreen"
    }
    val ContactChangePhoneCodeScreen = object : NavScreenWithArgument {
        override val routeWithArgument: String = "ContactChangePhoneCodeScreen/{phone}"
        override val argument0: String = "phone"
    }
}
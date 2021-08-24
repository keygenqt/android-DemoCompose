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
 
package com.keygenqt.demo_contacts.base

interface NavScreen {
    val route: String
}

interface NavScreenWithArgument {
    val routeWithArgument: String
    val argument0: String
    fun getRoute(argument0: String?): String {
        return routeWithArgument
            .replace("{${this.argument0}}", argument0 ?: "")
    }
}

interface NavScreenWithArgument2 : NavScreenWithArgument {
    val argument1: String
    fun getRoute(argument0: String?, argument1: String?): String {
        return getRoute(argument0)
            .replace("{${this.argument1}}", argument1 ?: "")
    }
}

interface NavScreenWithArgument3 : NavScreenWithArgument2 {
    val argument2: String
    fun getRoute(
        argument0: String?,
        argument1: String?,
        argument2: String?,
    ): String {
        return getRoute(argument0, argument1)
            .replace("{${this.argument2}}", argument2 ?: "")
    }
}

interface NavScreenWithArgument4 : NavScreenWithArgument3 {
    val argument3: String
    fun getRoute(
        argument0: String?,
        argument1: String?,
        argument2: String?,
        argument3: String?,
    ): String {
        return getRoute(argument0, argument1, argument2)
            .replace("{${this.argument3}}", argument3 ?: "")
    }
}

interface NavScreenWithArgument5 : NavScreenWithArgument4 {
    val argument4: String
    fun getRoute(
        argument0: String?,
        argument1: String?,
        argument2: String?,
        argument3: String?,
        argument4: String?,
    ): String {
        return getRoute(argument0, argument1, argument2, argument3)
            .replace("{${this.argument4}}", argument4 ?: "")
    }
}
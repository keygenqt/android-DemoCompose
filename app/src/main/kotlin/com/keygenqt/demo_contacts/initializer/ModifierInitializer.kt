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

@file:Suppress("unused")

package com.keygenqt.demo_contacts.initializer

import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.startup.Initializer
import com.keygenqt.modifier.ModifierConfiguration

class ModifierInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        ModifierConfiguration.init(
            xSmall = 2.dp,
            small = 4.dp,
            medium = 8.dp,
            large = 16.dp,
            xLarge = 24.dp,
            xLarge2 = 48.dp,
            xLarge3 = 96.dp,
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

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
 
package com.keygenqt.demo_contacts.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

// system palette colors
val DarkColorPalette: @Composable () -> Colors by lazy {
    { parseConfigPalette(false) }
}

val LightColorPalette: @Composable () -> Colors by lazy {
    { parseConfigPalette(true) }
}

@Composable
fun MyTheme(content: @Composable () -> Unit) {

    val colors = if (isSystemInDarkTheme()) {
        DarkColorPalette()
    } else {
        LightColorPalette()
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

// custom palette colors
val DarkColorCustomPalette: @Composable () -> CustomColors by lazy {
    { parseConfigCustomPalette(false) }
}

val LightColorCustomPalette: @Composable () -> CustomColors by lazy {
    { parseConfigCustomPalette(true) }
}

object MaterialThemeCustom {
    val colors: CustomColors
        @Composable
        get() {
            return if (isSystemInDarkTheme()) {
                DarkColorCustomPalette()
            } else {
                LightColorCustomPalette()
            }
        }
}
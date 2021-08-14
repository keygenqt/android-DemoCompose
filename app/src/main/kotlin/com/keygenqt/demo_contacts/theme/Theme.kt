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
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

// system palette colors
val DarkColorPalette by lazy {
    parseRemoteConfigPalette(false)
}

val LightColorPalette by lazy {
    parseRemoteConfigPalette(true)
}

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

// custom palette colors
val DarkColorCustomPalette by lazy {
    parseRemoteConfigCustomPalette(false)
}

val LightColorCustomPalette by lazy {
    parseRemoteConfigCustomPalette(true)
}

object MaterialThemeCustom {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() {
            return if (isSystemInDarkTheme()) {
                DarkColorCustomPalette
            } else {
                LightColorCustomPalette
            }
        }
}
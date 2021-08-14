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

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

class CustomColors(
    val splash: Color,
    val isLight: Boolean,
)

fun parseRemoteConfigCustomPalette(isLight: Boolean): CustomColors {
    return CustomColors(
        splash = Color(0xFF2D2F31),
        isLight = isLight,
    )
}

fun parseRemoteConfigPalette(isLight: Boolean): Colors {
    return Colors(
        primary = Color(0xFFBB86FC),
        primaryVariant = Color(0xFF3700B3),
        secondary = Color(0xFF03DAC6),
        secondaryVariant = Color(0xFF03DAC6),
        background = Color(0xFF121212),
        surface = Color(0xFF121212),
        error = Color(0xFFCF6679),
        onPrimary = Color(0xFF000000),
        onSecondary = Color(0xFF000000),
        onBackground = Color(0xFFFFFFFF),
        onSurface = Color(0xFFFFFFFF),
        onError = Color(0xFF000000),
        isLight = isLight,
    )
}
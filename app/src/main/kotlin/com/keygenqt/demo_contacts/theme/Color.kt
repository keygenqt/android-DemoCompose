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
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.keygenqt.demo_contacts.R

class CustomColors(
    val splash: Color,
    val customTitle: Color,
    val tabEnable: Color,
    val tabDisable: Color,
    val textColorSecondary: Color,
    val isLight: Boolean,
)

@Composable
fun parseConfigCustomPalette(isLight: Boolean): CustomColors {
    return CustomColors(
        splash = colorResource(R.color.splash),
        customTitle = colorResource(R.color.customTitle),
        tabEnable = colorResource(R.color.tabEnable),
        tabDisable = colorResource(R.color.tabDisable),
        textColorSecondary = colorResource(R.color.textColorSecondary),
        isLight = isLight,
    )
}

@Composable
fun parseConfigPalette(isLight: Boolean): Colors {
    return Colors(
        primary = colorResource(R.color.primary),
        primaryVariant = colorResource(R.color.primaryVariant),
        secondary = colorResource(R.color.secondary),
        secondaryVariant = colorResource(R.color.secondaryVariant),
        background = colorResource(R.color.background),
        surface = colorResource(R.color.surface),
        error = colorResource(R.color.error),
        onPrimary = colorResource(R.color.onPrimary),
        onSecondary = colorResource(R.color.onSecondary),
        onBackground = colorResource(R.color.onBackground),
        onSurface = colorResource(R.color.onSurface),
        onError = colorResource(R.color.onError),
        isLight = isLight,
    )
}
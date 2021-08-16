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
 
package com.keygenqt.demo_contacts.modules.cart.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.cart.ui.events.CartEvents
import com.keygenqt.demo_contacts.modules.cart.ui.viewModels.CartViewModel
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.MainScaffold
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom

@ExperimentalComposeUiApi
@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onNavigationEvent: (CartEvents) -> Unit = {},
) {
    CartBody(
        onNavigationEvent = onNavigationEvent,
    )
}

@ExperimentalComposeUiApi
@Composable
fun CartBody(
    onNavigationEvent: (CartEvents) -> Unit = {},
) {
    MainScaffold {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                text = stringResource(id = R.string.common_coming_soon),
            )
            Text(
                color = MaterialThemeCustom.colors.customTitle,
                style = MaterialTheme.typography.subtitle1,
                text = stringResource(id = R.string.cart_title),
            )
        }
    }
}

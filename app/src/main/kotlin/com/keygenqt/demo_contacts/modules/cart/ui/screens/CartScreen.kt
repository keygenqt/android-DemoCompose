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

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.accompanist.MainScaffoldSearch
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.EmptyListScreen
import com.keygenqt.demo_contacts.modules._common.ui.compose.TopBarContentSubtitle
import com.keygenqt.demo_contacts.modules._common.ui.compose.TopBarContentTitle
import com.keygenqt.demo_contacts.modules.cart.ui.events.CartEvents
import com.keygenqt.demo_contacts.modules.cart.ui.viewModels.CartViewModel
import com.keygenqt.demo_contacts.theme.MyTheme
import com.keygenqt.modifier.sizeXSmall

@ExperimentalComposeUiApi
@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onEvent: (CartEvents) -> Unit = {},
) {
    CartBody(
        onEvent = onEvent,
    )
}

@ExperimentalComposeUiApi
@Composable
fun CartBody(
    onEvent: (CartEvents) -> Unit = {},
) {
    MainScaffoldSearch(
        contentTitle = {
            TopBarContentTitle(stringResource(id = R.string.cart_title).uppercase(), TextAlign.Center)
            Spacer(modifier = Modifier.sizeXSmall())
            TopBarContentSubtitle(stringResource(id = R.string.cart_subtitle, (12345678..87654321).random()))
        },
    ) {
        EmptyListScreen(
            title = stringResource(id = R.string.cart_empty_title),
            text = stringResource(id = R.string.cart_empty_text),
            painter = painterResource(id = R.drawable.ic_cart_empty_state)
        )
    }
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyListScreenPreview() {
    MyTheme {
        Scaffold {
            CartBody()
        }
    }
}
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
 
package com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangeEmailScreen

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.modules._common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangeEmailEvents
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalComposeUiApi
@Composable
fun ContactChangeEmailBody(
    onEvent: (ContactChangeEmailEvents) -> Unit = {},
) {
    MainScaffold(
        title = "",
        elevation = 0.dp,
        icon = Icons.Filled.ArrowBack,
        navigationIconOnClick = {
            onEvent(ContactChangeEmailEvents.NavigateBack)
        }
    ) {

    }
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactChangeEmailBodyPreview() {
    MyTheme {
        Surface {
            ContactChangeEmailBody()
        }
    }
}



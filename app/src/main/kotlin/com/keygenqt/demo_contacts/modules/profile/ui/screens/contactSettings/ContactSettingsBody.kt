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

package com.keygenqt.demo_contacts.modules.profile.ui.screens.contactSettings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactSettingsEvents
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ContactSettingsBody(
    onEvent: (ContactSettingsEvents) -> Unit = {},
    argumentUpdatedEmail: String? = null,
    argumentUpdatedPhone: String? = null,
) {
    Box {
        MainScaffold(
            title = stringResource(id = R.string.contact_settings_title),
            icon = Icons.Filled.ArrowBack,
            navigationIconOnClick = {
                onEvent(ContactSettingsEvents.NavigateBack)
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ContactSettingsBodySms(
                    onEvent = onEvent,
                    argumentUpdatedPhone = argumentUpdatedPhone,
                )
                ContactSettingsBodyEmail(
                    onEvent = onEvent,
                    argumentUpdatedEmail = argumentUpdatedEmail,
                )
            }
        }

        UpdatedBottomSheetInfo(
            isShow = argumentUpdatedEmail != null,
            text = stringResource(id = R.string.contact_settings_added_email)
        )

        UpdatedBottomSheetInfo(
            isShow = argumentUpdatedPhone != null,
            text = stringResource(id = R.string.contact_settings_added_phone)
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactSettingsBodyPreview() {
    MyTheme {
        Surface {
            ContactSettingsBody()
        }
    }
}


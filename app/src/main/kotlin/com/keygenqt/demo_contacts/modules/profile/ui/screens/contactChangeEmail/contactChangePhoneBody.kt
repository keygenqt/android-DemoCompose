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

package com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangeEmail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.accompanist.MainScaffoldSearch
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.FormError
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangeEmailEvents
import com.keygenqt.demo_contacts.theme.MyTheme
import com.keygenqt.modifier.sizeLarge

@ExperimentalComposeUiApi
@Composable
fun ContactChangeEmailBody(
    loading: Boolean = false,
    commonError: String? = null,
    onEvent: (ContactChangeEmailEvents) -> Unit = {},
) {
    MainScaffoldSearch(
        contentTitle = {},
        topBarElevation = 0.dp,
        contentLoadState = loading,
        navigationIcon = Icons.Filled.ArrowBack,
        navigationIconOnClick = {
            onEvent(ContactChangeEmailEvents.NavigateBack)
        }
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.contact_change_email_title),
                style = MaterialTheme.typography.h4,
            )

            Spacer(modifier = Modifier.sizeLarge())

            Text(
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
                text = stringResource(id = R.string.contact_change_email_text),
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.sizeLarge())

            // common error
            commonError?.let {
                FormError(textError = it)
                Spacer(modifier = Modifier.sizeLarge())
            }

            ContactChangeEmailForm(
                loading = loading,
                onEvent = onEvent
            )

        }
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



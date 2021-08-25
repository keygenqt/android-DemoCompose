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
 
package com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangePhoneCode

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
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.FormError
import com.keygenqt.demo_contacts.modules._common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangePhoneCodeEvents
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalComposeUiApi
@Composable
fun ContactChangePhoneCodeBody(
    phone: String,
    loading: Boolean = false,
    loadingRefresh: Int = 0,
    commonError: String? = null,
    onEvent: (ContactChangePhoneCodeEvents) -> Unit = {},
) {
    MainScaffold(
        title = "",
        elevation = 0.dp,
        isLoaderShow = loading,
        icon = Icons.Filled.ArrowBack,
        navigationIconOnClick = {
            onEvent(ContactChangePhoneCodeEvents.NavigateBack)
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
                text = stringResource(id = R.string.contact_change_phone_title),
                style = MaterialTheme.typography.h4,
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
                text = stringResource(id = R.string.contact_change_common_code_text, phone),
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.size(16.dp))

            // common error
            commonError?.let {
                FormError(textError = stringResource(id = R.string.common_check_failed))
                Spacer(Modifier.size(16.dp))
            }

            ContactChangePhoneCodeForm(
                isError = commonError != null,
                loading = loading,
                onEvent = onEvent,
                loadingRefresh = loadingRefresh,
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactChangePhoneBodyPreview() {
    MyTheme {
        Surface {
            ContactChangePhoneCodeBody("+7 3434 343 34")
        }
    }
}



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
 
package com.keygenqt.demo_contacts.modules.profile.ui.screens.profileScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import com.keygenqt.demo_contacts.theme.MyTheme
import com.keygenqt.modifier.sizeLarge
import com.keygenqt.modifier.sizeXLarge

@Composable
fun ProfileAddCardBody(
    onEvent: (ProfileEvents) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.profile_add_card_title),
            style = MaterialTheme.typography.h5,
        )

        Spacer(modifier = Modifier.sizeLarge())

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialThemeCustom.colors.textColorSecondary,
            text = stringResource(id = R.string.profile_add_card_text),
            style = MaterialTheme.typography.subtitle1,
        )

        Spacer(modifier = Modifier.sizeXLarge())

        OutlinedButton(
            shape = MaterialTheme.shapes.large,
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                color = MaterialTheme.colors.onPrimary,
                text = stringResource(id = R.string.profile_add_card_btn),
            )
        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileAddCardBodyPreview() {
    MyTheme {
        Surface {
            ProfileAddCardBody()
        }
    }
}
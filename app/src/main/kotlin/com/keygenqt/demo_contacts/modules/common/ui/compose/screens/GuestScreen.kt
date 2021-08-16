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
 
package com.keygenqt.demo_contacts.modules.common.ui.compose.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.ClickableTextAnimation
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.PlugBlock
import com.keygenqt.demo_contacts.theme.MyTheme

@Composable
fun GuestListScreen(
    text: String = "Long text for preview",
    painter: Painter = painterResource(id = R.drawable.ic_favorite_placeholder),
) {
    val context = LocalContext.current

    PlugBlock(
        text = text,
        painter = painter
    ) {
        Spacer(modifier = Modifier.size(24.dp))

        Button(
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.secondary),
            onClick = {
                Toast.makeText(context, R.string.common_coming_soon, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                color = MaterialTheme.colors.onSecondary,
                text = stringResource(id = R.string.common_sign_in),
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        ClickableTextAnimation(
            text = stringResource(id = R.string.common_sign_up)
        ) {
            Toast.makeText(context, R.string.common_coming_soon, Toast.LENGTH_SHORT).show()
        }
    }
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GuestScreenPreview() {
    MyTheme {
        Scaffold {
            GuestListScreen()
        }
    }
}
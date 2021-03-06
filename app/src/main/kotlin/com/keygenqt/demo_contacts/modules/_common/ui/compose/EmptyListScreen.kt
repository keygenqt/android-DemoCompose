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
 
package com.keygenqt.demo_contacts.modules._common.ui.compose

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.theme.MyTheme
import com.keygenqt.modifier.sizeLarge
import com.keygenqt.modifier.sizeXLarge

@Composable
fun EmptyListScreen(
    title: String? = null,
    text: String? = null,
    painter: Painter = painterResource(id = R.drawable.ic_common_empty),
) {
    val context = LocalContext.current

    PlugBlock(
        title = title,
        text = text,
        painter = painter
    ) {
        Spacer(modifier = Modifier.sizeXLarge())

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedButton(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    Toast.makeText(context, R.string.common_coming_soon, Toast.LENGTH_SHORT).show()
                },
            ) {
                Text(
                    color = MaterialTheme.colors.onPrimary,
                    text = stringResource(id = R.string.common_go_to_catalog),
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyListScreenPreview() {
    MyTheme {
        Scaffold {
            EmptyListScreen(
                text = "Long text for preview"
            )
        }
    }
}
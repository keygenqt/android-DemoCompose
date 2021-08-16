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
 
package com.keygenqt.demo_contacts.modules.other.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import com.keygenqt.demo_contacts.theme.MyTheme
import java.util.*

@Composable
fun StartScreen2(onNext: () -> Unit = {}) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .padding(bottom = 64.dp)
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .size(248.dp)
                        .align(Alignment.Center)
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        painter = painterResource(R.drawable.ic_other_start_location),
                        contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.start_2_title).uppercase(),
                style = MaterialTheme.typography.h6,
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialThemeCustom.colors.textColorSecondary,
                text = stringResource(id = R.string.start_2_text),
                style = MaterialTheme.typography.subtitle1,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Button(
                colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.secondary),
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    color = MaterialTheme.colors.onSecondary,
                    text = stringResource(id = R.string.start_2_btn_permissions),
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedButton(
                    modifier = Modifier.align(Alignment.Center),
                    border = BorderStroke(0.dp, MaterialTheme.colors.background),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {
                        Toast.makeText(context, R.string.common_coming_soon, Toast.LENGTH_SHORT).show()
                    },
                ) {
                    Text(
                        color = MaterialTheme.colors.onPrimary,
                        text = stringResource(id = R.string.start_2_btn_select),
                    )
                }
            }

        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StartScreen2Preview() {
    MyTheme {
        Scaffold {
            StartScreen2()
        }
    }
}
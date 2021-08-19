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
 
package com.keygenqt.demo_contacts.modules.other.ui.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.PlugBlock
import com.keygenqt.demo_contacts.theme.MyTheme

@Composable
fun OnboardingItem1(onNext: () -> Unit = {}) {
    PlugBlock(
        title = stringResource(id = R.string.start_1_title),
        text = stringResource(id = R.string.start_1_text),
        painter = painterResource(R.drawable.ic_other_start),
        contentBoxScope = {
            Button(
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.secondary),
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    color = MaterialTheme.colors.onSecondary,
                    text = stringResource(id = R.string.start_1_btn),
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    )
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StartScreen1Preview() {
    MyTheme {
        Scaffold {
            OnboardingItem1()
        }
    }
}
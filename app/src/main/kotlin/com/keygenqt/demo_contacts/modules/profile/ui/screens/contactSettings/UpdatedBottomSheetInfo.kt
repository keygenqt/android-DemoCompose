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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.BottomSheetScaffoldInfo
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import com.keygenqt.modifier.sizeXLarge
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun UpdatedBottomSheetInfo(
    text: String,
    isShow: Boolean = false,
) {
    val scope = rememberCoroutineScope()
    var isShowInfo: Boolean by rememberSaveable { mutableStateOf(isShow) }

    BottomSheetScaffoldInfo(
        isShow = isShowInfo
    ) { state: BottomSheetScaffoldState ->

        Column(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Spacer(modifier = Modifier.sizeXLarge())

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_profile_popup),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.sizeXLarge())

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialThemeCustom.colors.textColorSecondary,
                text = text,
                style = MaterialTheme.typography.subtitle1,
            )

            Spacer(modifier = Modifier.sizeXLarge())

            OutlinedButton(
                shape = MaterialTheme.shapes.large,
                onClick = {
                    scope.launch {
                        state.bottomSheetState.collapse()
                    }
                    scope.launch {
                        delay(100L)
                        isShowInfo = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    color = MaterialTheme.colors.onPrimary,
                    text = stringResource(id = R.string.common_btn_cancel),
                )
            }
        }
    }
}
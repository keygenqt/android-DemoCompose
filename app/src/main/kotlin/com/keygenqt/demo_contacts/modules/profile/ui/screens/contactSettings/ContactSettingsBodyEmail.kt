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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.profile.data.mock.mockUserContactsModel
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactsModel
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactSettingsEvents
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalComposeUiApi
@Composable
fun ContactSettingsBodyEmail(
    model: UserContactsModel,
    onEvent: (ContactSettingsEvents) -> Unit = {},
    argumentUpdatedEmail: String? = null,
    errorEmail: () -> Unit = {},
    channelEmail: () -> Unit = {},
) {
    val checkedStateStateOrder = remember { mutableStateOf(model.email.notifyMailShort) }
    val checkedStateStock = remember { mutableStateOf(model.email.notifyMailFull) }

    LaunchedEffect(model.email.notifyMailShort) {
        checkedStateStateOrder.value = model.email.notifyMailShort
    }

    LaunchedEffect(model.email.notifyMailFull) {
        checkedStateStock.value = model.email.notifyMailFull
    }

    var data = argumentUpdatedEmail ?: model.email.contactEmail
    if (data.isEmpty()) {
        data = stringResource(id = R.string.contact_settings_value_empty)
    }

    Text(
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.h5,
        text = stringResource(id = R.string.contact_settings_subtitle_email),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    )

    Card(
        shape = MaterialTheme.shapes.large,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clickable(onClick = {
                onEvent(ContactSettingsEvents.NavigateToContactChangeEmail)
            })
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            val (text, value, icon) = createRefs()

            Text(
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
                text = stringResource(id = R.string.contact_settings_label_email),
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp)
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .constrainAs(value) {
                        top.linkTo(parent.top)
                        end.linkTo(icon.start)
                        start.linkTo(text.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
            ) {
                Text(
                    style = MaterialTheme.typography.body2,
                    text = data,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .height(12.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }

    if (model.email.contactEmail.isNotEmpty() || argumentUpdatedEmail != null) {
        val short: () -> Unit = {
            if (model.phone.contactPhone.isEmpty() || !model.phone.notifySmsShort) {
                errorEmail.invoke()
            } else {
                checkedStateStateOrder.value = !checkedStateStateOrder.value
                onEvent(ContactSettingsEvents.UpdateStatusSmall(
                    statusEmail = checkedStateStateOrder.value,
                    statusPhone = model.phone.notifySmsShort
                ))
            }
        }

        Card(
            shape = MaterialTheme.shapes.large,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .clickable(onClick = short)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {
                val (text, switch) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(text) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(switch.start)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        }
                ) {
                    Text(
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1,
                        text = stringResource(id = R.string.contact_settings_label_status),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                }

                Switch(
                    checked = checkedStateStateOrder.value,
                    onCheckedChange = { short.invoke() },
                    colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.secondary),
                    modifier = Modifier
                        .constrainAs(switch) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }
        }

        val full: () -> Unit = {
            if (checkedStateStock.value && model.phone.contactPhone.isEmpty()) {
                channelEmail.invoke()
            }
            checkedStateStock.value = !checkedStateStock.value
            onEvent(ContactSettingsEvents.UpdateStatusFull(
                statusEmail = checkedStateStock.value,
                statusPhone = model.phone.notifySmsFull
            ))
        }

        Card(
            shape = MaterialTheme.shapes.large,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .clickable(onClick = full)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {
                val (text, switch) = createRefs()

                Box(
                    modifier = Modifier
                        .constrainAs(text) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(switch.start)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        }
                ) {
                    Text(
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1,
                        text = stringResource(id = R.string.contact_settings_label_stock),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                }

                Switch(
                    checked = checkedStateStock.value,
                    onCheckedChange = { full.invoke() },
                    colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.secondary),
                    modifier = Modifier
                        .constrainAs(switch) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactSettingsBodyEmailPreview() {
    MyTheme {
        Surface {
            Column {
                ContactSettingsBodyEmail(model = mockUserContactsModel())
            }
        }
    }
}
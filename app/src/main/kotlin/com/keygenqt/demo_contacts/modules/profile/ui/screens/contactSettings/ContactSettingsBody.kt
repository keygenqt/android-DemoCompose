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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.LoadingScreen
import com.keygenqt.demo_contacts.modules._common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactsModel
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactSettingsEvents

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ContactSettingsBody(
    userContacts: Any?,
    loadingActionBar: Boolean,
    loading: Boolean,
    onEvent: (ContactSettingsEvents) -> Unit = {},
    argumentUpdatedEmail: String? = null,
    argumentUpdatedPhone: String? = null,
) {

    var errorEmail: Boolean by remember { mutableStateOf(false) }
    var channelEmail: Boolean by remember { mutableStateOf(false) }
    var errorPhone: Boolean by remember { mutableStateOf(false) }
    var channelPhone: Boolean by remember { mutableStateOf(false) }

    Box {
        MainScaffold(
            isLoaderShow = loadingActionBar,
            title = stringResource(id = R.string.contact_settings_title),
            icon = Icons.Filled.ArrowBack,
            navigationIconOnClick = {
                onEvent(ContactSettingsEvents.NavigateBack)
            }
        ) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(loading),
                onRefresh = {
                    onEvent(ContactSettingsEvents.UpdateUserContacts)
                },
                indicator = { st, tr ->
                    SwipeRefreshIndicator(
                        state = st,
                        refreshTriggerDistance = tr,
                        contentColor = MaterialTheme.colors.onPrimary,
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    if (userContacts is UserContactsModel) {
                        ContactSettingsBodySms(
                            model = userContacts,
                            onEvent = onEvent,
                            argumentUpdatedPhone = argumentUpdatedPhone,
                            errorPhone = { errorPhone = true },
                            channelPhone = { channelPhone = true }
                        )
                        ContactSettingsBodyEmail(
                            model = userContacts,
                            onEvent = onEvent,
                            argumentUpdatedEmail = argumentUpdatedEmail,
                            errorEmail = { errorEmail = true },
                            channelEmail = { channelEmail = true }
                        )
                    }
                }

                if (userContacts !is UserContactsModel) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                    ) {
                        if (userContacts == null) {
                            LoadingScreen(loading)
                        }
                    }
                }
            }
        }

        ContactSettingsNotifications(
            argumentUpdatedEmail = argumentUpdatedEmail,
            argumentUpdatedPhone = argumentUpdatedPhone,
        )

        // error phone
        NotificationsBottomSheetInfo(
            isShowBtn1 = if (userContacts is UserContactsModel) userContacts.email.contactEmail.isNotBlank() else false,
            isShow = errorPhone,
            title = stringResource(id = R.string.contact_settings_error_notifications_title),
            text = stringResource(id = R.string.contact_settings_error_notifications_text_phone),
            btn1 = stringResource(id = R.string.contact_settings_error_notifications_btn_email),
            onBtn1 = {
                errorPhone = false
                onEvent(ContactSettingsEvents.UpdateStatusSmall(statusEmail = true, statusPhone = false))
            },
            onBtn2 = {
                errorPhone = false
            }
        )

        // error email
        NotificationsBottomSheetInfo(
            isShowBtn1 = if (userContacts is UserContactsModel) userContacts.phone.contactPhone.isNotBlank() else false,
            isShow = errorEmail,
            title = stringResource(id = R.string.contact_settings_error_notifications_title),
            text = stringResource(id = R.string.contact_settings_error_notifications_text_email),
            btn1 = stringResource(id = R.string.contact_settings_error_notifications_btn_phone),
            onBtn1 = {
                errorEmail = false
                onEvent(ContactSettingsEvents.UpdateStatusSmall(statusEmail = false, statusPhone = true))
            },
            onBtn2 = {
                errorEmail = false
            }
        )

        // channel phone
        NotificationsBottomSheetInfo(
            isShow = channelPhone,
            title = stringResource(id = R.string.contact_settings_channels_title_phone),
            text = stringResource(id = R.string.contact_settings_channels_text_phone),
            btn1 = stringResource(id = R.string.contact_settings_channels_btn_phone),
            onBtn1 = {
                channelPhone = false
                onEvent(ContactSettingsEvents.NavigateToContactChangeEmail)
            },
            onBtn2 = {
                channelPhone = false
            }
        )

        // channel email
        NotificationsBottomSheetInfo(
            isShow = channelEmail,
            title = stringResource(id = R.string.contact_settings_channels_title_email),
            text = stringResource(id = R.string.contact_settings_channels_text_email),
            btn1 = stringResource(id = R.string.contact_settings_channels_btn_email),
            onBtn1 = {
                channelEmail = false
                onEvent(ContactSettingsEvents.NavigateToContactChangePhone)
            },
            onBtn2 = {
                channelEmail = false
            }
        )
    }
}


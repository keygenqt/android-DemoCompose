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

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactSettingsEvents

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ContactSettingsNotifications(
    argumentUpdatedEmail: String? = null,
    argumentUpdatedPhone: String? = null,
) {
    UpdatedBottomSheetInfo(
        isShow = argumentUpdatedEmail != null,
        text = stringResource(id = R.string.contact_settings_added_email)
    )

    UpdatedBottomSheetInfo(
        isShow = argumentUpdatedPhone != null,
        text = stringResource(id = R.string.contact_settings_added_phone)
    )
}


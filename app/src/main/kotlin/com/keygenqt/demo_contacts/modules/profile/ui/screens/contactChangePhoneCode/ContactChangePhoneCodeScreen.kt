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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangePhoneCodeEvents
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileChangeContactsViewModel
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileContactsViewModel
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileViewModel

@ExperimentalComposeUiApi
@Composable
fun ContactChangePhoneCodeScreen(
    phone: String,
    viewModel: ProfileChangeContactsViewModel,
    onEvent: (ContactChangePhoneCodeEvents) -> Unit = {},
) {

    val loading: Boolean by viewModel.loading.collectAsState()
    val loadingRefresh: Int by viewModel.loadingRefresh.collectAsState()
    val commonError: String? by viewModel.commonError.collectAsState(null)

    ContactChangePhoneCodeBody(
        phone = phone,
        loading = loading,
        loadingRefresh = loadingRefresh,
        commonError = commonError,
        onEvent = onEvent,
    )
}



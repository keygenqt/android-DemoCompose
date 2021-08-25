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

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.modules._common.ui.compose.ErrorNetworkScreen
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import com.keygenqt.demo_contacts.modules.profile.ui.viewModels.ProfileViewModel

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onEvent: (ProfileEvents) -> Unit = {},
) {

    val user by viewModel.getUser().collectAsState(false)
    val loading: Boolean by viewModel.loading.collectAsState()
    val isLogin by LocalBaseViewModel.current.isLogin.collectAsState()

    ProfileBody(
        user = user,
        loading = loading,
        isLogin = isLogin,
        onEvent = onEvent,
    )

    if (viewModel.errorConnection.collectAsState().value) {
        ErrorNetworkScreen(loading)
    }
}



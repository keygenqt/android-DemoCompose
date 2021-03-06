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

package com.keygenqt.demo_contacts.modules.other.ui.screens.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keygenqt.accompanist.MainScaffoldSearch
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.compose.FormError
import com.keygenqt.demo_contacts.modules._common.ui.compose.TopBarContentTitle
import com.keygenqt.demo_contacts.modules.other.ui.events.SignInEvents
import com.keygenqt.modifier.sizeLarge

@ExperimentalComposeUiApi
@Composable
fun SignInBody(
    loading: Boolean = false,
    commonError: String? = null,
    onEvent: (SignInEvents) -> Unit = {},
) {
    MainScaffoldSearch(
        contentLoadState = loading,
        contentTitle = {
            TopBarContentTitle(stringResource(id = R.string.sign_in_title))
        },
        navigationIcon = Icons.Filled.ArrowBack,
        navigationIconOnClick = {
            onEvent(SignInEvents.NavigateBack)
        }
    ) {

        val padding = 16.dp
        val listState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(start = padding, end = padding)
                .background(MaterialTheme.colors.background)
                .verticalScroll(listState)
        ) {

            Spacer(modifier = Modifier.sizeLarge())

            // common error
            commonError?.let {
                FormError(textError = it)
                Spacer(Modifier.size(padding))
                LaunchedEffect(commonError) { listState.animateScrollTo(0) }
            }

            SignInForm(
                loading = loading,
                onNavigationEvent = onEvent
            )

            Spacer(modifier = Modifier.sizeLarge())
        }
    }
}



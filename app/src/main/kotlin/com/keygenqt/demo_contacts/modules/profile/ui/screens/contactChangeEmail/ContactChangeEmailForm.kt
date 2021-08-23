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
 
package com.keygenqt.demo_contacts.modules.profile.ui.screens.contactChangeEmail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.form.base.FormFieldsState
import com.keygenqt.demo_contacts.modules._common.ui.form.fields.FieldEmail
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangeEmailEvents
import com.keygenqt.demo_contacts.modules.profile.ui.form.ChangeEmailFormStates
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun ContactChangeEmailForm(
    loading: Boolean = false,
    onEvent: (ContactChangeEmailEvents) -> Unit = {},
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val padding = 16.dp

    val formFields = FormFieldsState().apply {
        add(ChangeEmailFormStates.ChangeEmail, remember { ChangeEmailFormStates.ChangeEmail.state.default("") })
    }

    val requesterField = remember { FocusRequester() }

    // click submit
    val submitClick = {
        // validate before send
        formFields.validate()
        // check has errors
        if (!formFields.hasErrors()) {
            // submit query
            onEvent(
                ContactChangeEmailEvents.ContactChangeEmail(
                    email = formFields.get(ChangeEmailFormStates.ChangeEmail).getValue(),
                )
            )
            // hide keyboard
            localFocusManager.clearFocus()
            softwareKeyboardController?.hide()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            FieldEmail(
                modifier = Modifier.focusRequester(requesterField),
                labelText = stringResource(id = R.string.contact_change_email_label),
                enabled = !loading,
                state = formFields.get(ChangeEmailFormStates.ChangeEmail),
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(onNext = { submitClick.invoke() })
            )

            Spacer(modifier = Modifier.size(padding))
        }

        Button(
            enabled = !loading,
            onClick = { submitClick.invoke() },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.secondary),
        ) {
            Text(
                text = stringResource(id = R.string.contact_change_email_btn).uppercase(),
            )
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            delay(10)
            requesterField.requestFocus()
        }
    }
}

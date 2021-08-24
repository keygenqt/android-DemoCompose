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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.keygenqt.demo_contacts.modules._common.ui.form.fields.FieldPassword
import com.keygenqt.demo_contacts.modules.other.ui.events.SignInEvents
import com.keygenqt.demo_contacts.modules.other.ui.form.SignInFieldsForm.SignInEmail
import com.keygenqt.demo_contacts.modules.other.ui.form.SignInFieldsForm.SignInPassword
import com.keygenqt.demo_contacts.utils.ConstantsApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun SignInForm(
    loading: Boolean = false,
    onNavigationEvent: (SignInEvents) -> Unit = {},
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val padding = 16.dp

    val formFields = FormFieldsState().apply {
        add(SignInEmail, remember { SignInEmail.state.default(ConstantsApp.DEBUG_CREDENTIAL_LOGIN) })
        add(SignInPassword, remember { SignInPassword.state.default(ConstantsApp.DEBUG_CREDENTIAL_PASSW) })
    }

    val requesterFieldEmail = remember { FocusRequester() }
    val requesterFieldPassword = remember { FocusRequester() }

    // click submit
    val submitClick = {
        // validate before send
        formFields.validate()
        // check has errors
        if (!formFields.hasErrors()) {
            // submit query
            onNavigationEvent(
                SignInEvents.SignIn(
                    login = formFields.get(SignInEmail).getValue(),
                    passw = formFields.get(SignInPassword).getValue(),
                )
            )
            // hide keyboard
            localFocusManager.clearFocus()
            softwareKeyboardController?.hide()
        }
    }

    FieldEmail(
        modifier = Modifier.focusRequester(requesterFieldEmail),
        labelText = stringResource(id = R.string.form_email),
        enabled = !loading,
        state = formFields.get(SignInEmail),
        imeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = { requesterFieldPassword.requestFocus() })
    )

    Spacer(modifier = Modifier.size(padding))

    FieldPassword(
        modifier = Modifier.focusRequester(requesterFieldPassword),
        enabled = !loading,
        state = formFields.get(SignInPassword),
        imeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = { submitClick.invoke() })
    )

    Spacer(modifier = Modifier.size(padding))

    Button(
        enabled = !loading,
        onClick = { submitClick.invoke() },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.secondary),
    ) {
        Text(
            text = stringResource(id = R.string.form_button_submit).uppercase(),
        )
    }

    LaunchedEffect(Unit) {
        scope.launch {
            delay(10)
            requesterFieldEmail.requestFocus()
        }
    }
}
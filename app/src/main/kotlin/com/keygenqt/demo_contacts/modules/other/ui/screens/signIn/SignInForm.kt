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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.other.ui.events.SignInEvents
import com.keygenqt.demo_contacts.modules.other.ui.form.SignInFieldsForm.*
import com.keygenqt.demo_contacts.theme.customTextFieldColors
import com.keygenqt.demo_contacts.utils.ConstantsApp
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.forms.fields.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInForm(
    loading: Boolean = false,
    onNavigationEvent: (SignInEvents) -> Unit = {},
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val padding = 16.dp

    // Create from state
    val formFields = FormFieldsState().apply {
        add(SignInEmail, remember { SignInEmail.state.default(ConstantsApp.DEBUG_CREDENTIAL_LOGIN) })
        add(SignInPassword, remember { SignInPassword.state.default(ConstantsApp.DEBUG_CREDENTIAL_PASSW) })
        add(SignInPhoneEmoji, remember { SignInPhoneEmoji.state.default("") })
        add(SignInPhoneUA, remember { SignInPhoneUA.state.default("") })
        add(SignInPhoneRU, remember { SignInPhoneRU.state.default("") })
        add(SignInPhoneCustom, remember { SignInPhoneCustom.state.default("") })
        add(SignInCard, remember { SignInCard.state.default("") })
    }

    // For focus to field
    val requesterFieldEmail = remember { FocusRequester() }
    val requesterFieldEmoji = remember { FocusRequester() }
    val requesterFieldPhoneUA = remember { FocusRequester() }
    val requesterFieldPhoneRU = remember { FocusRequester() }
    val requesterFieldPhoneCustom = remember { FocusRequester() }
    val requesterFieldCard = remember { FocusRequester() }
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

    // Create field email
    FormFieldEmail(
        modifier = Modifier.focusRequester(requesterFieldEmail),
        label = stringResource(id = R.string.form_email),
        enabled = !loading,
        state = formFields.get(SignInEmail),
        imeAction = ImeAction.Next,
        colors = customTextFieldColors(),
        keyboardActions = KeyboardActions(onNext = { requesterFieldEmoji.requestFocus() })
    )

    Spacer(modifier = Modifier.size(padding))

    FormField(
        modifier = Modifier.focusRequester(requesterFieldEmoji),
        label = stringResource(id = R.string.form_name),
        enabled = !loading,
        state = formFields.get(SignInPhoneEmoji),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Ascii,
        colors = customTextFieldColors(),
        keyboardActions = KeyboardActions(onNext = { requesterFieldPhoneUA.requestFocus() }),
        filterEmoji = true
    )

    Spacer(modifier = Modifier.size(padding))

    FormFieldPhone(
        modifier = Modifier.focusRequester(requesterFieldPhoneUA),
        label = stringResource(id = R.string.form_phone),
        enabled = !loading,
        state = formFields.get(SignInPhoneUA),
        imeAction = ImeAction.Next,
        colors = customTextFieldColors(),
        keyboardActions = KeyboardActions(onNext = { requesterFieldPhoneRU.requestFocus() }),
        mask = "+380 (###) ###-##-##",
        placeholder = "+380 (000) 000-000-000",
    )

    Spacer(modifier = Modifier.size(padding))

    FormFieldPhone(
        modifier = Modifier.focusRequester(requesterFieldPhoneRU),
        label = stringResource(id = R.string.form_phone),
        enabled = !loading,
        state = formFields.get(SignInPhoneRU),
        imeAction = ImeAction.Next,
        colors = customTextFieldColors(),
        keyboardActions = KeyboardActions(onNext = { requesterFieldPhoneCustom.requestFocus() }),
        mask = "+7 (###) ###-##-##",
        placeholder = "+7 (000) 000-000-000",
    )

    Spacer(modifier = Modifier.size(padding))

    FormFieldPhone(
        modifier = Modifier.focusRequester(requesterFieldPhoneCustom),
        label = stringResource(id = R.string.form_phone),
        enabled = !loading,
        state = formFields.get(SignInPhoneCustom),
        imeAction = ImeAction.Next,
        colors = customTextFieldColors(),
        keyboardActions = KeyboardActions(onNext = { requesterFieldCard.requestFocus() }),
        mask = "+# (###) ###-##-##",
        placeholder = "+0 (000) 000-000-000",
    )

    Spacer(modifier = Modifier.size(padding))

    FormFieldNumber(
        modifier = Modifier.focusRequester(requesterFieldCard),
        label = stringResource(id = R.string.form_card),
        enabled = !loading,
        state = formFields.get(SignInCard),
        imeAction = ImeAction.Next,
        colors = customTextFieldColors(),
        keyboardActions = KeyboardActions(onNext = { requesterFieldPassword.requestFocus() }),
        mask = "####-####-####-####",
        placeholder = "0000-0000-0000-0000",
        filter = "123456789"
    )

    Spacer(modifier = Modifier.size(padding))

    // Create field password
    FormFieldPassword(
        modifier = Modifier.focusRequester(requesterFieldPassword),
        enabled = !loading,
        state = formFields.get(SignInPassword),
        filter = "123456789",
        maxLength = 10,
        imeAction = ImeAction.Done,
        colors = customTextFieldColors(),
        tintIcon = MaterialTheme.colors.onPrimary,
        keyboardActions = KeyboardActions(onDone = { submitClick.invoke() })
    )

    Spacer(modifier = Modifier.size(padding))

    // Submit button
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

    // Clear focus after end
    LaunchedEffect(Unit) {
        scope.launch {
            delay(10)
            requesterFieldEmail.requestFocus()
        }
    }
}
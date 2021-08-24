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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.form.base.FormFieldsState
import com.keygenqt.demo_contacts.modules._common.ui.form.fields.FieldSimpleEditText
import com.keygenqt.demo_contacts.modules.profile.ui.events.ContactChangePhoneCodeEvents
import com.keygenqt.demo_contacts.modules.profile.ui.form.ChangePhoneCodeFieldsForm.Code
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun ContactChangePhoneCodeForm(
    loadingRefresh: Int = 0,
    loading: Boolean = false,
    onEvent: (ContactChangePhoneCodeEvents) -> Unit = {},
) {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val formFields = FormFieldsState().apply {
        add(Code, remember { Code.state.default("") })
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
                ContactChangePhoneCodeEvents.ContactChangePhoneCode(
                    code = formFields.get(Code).getValue(),
                )
            )
            localFocusManager.clearFocus()
            softwareKeyboardController?.hide()
        }
    }

    FieldSimpleEditText(
        modifier = Modifier.focusRequester(requesterField),
        labelText = stringResource(id = R.string.contact_change_phone_code_label),
        enabled = !loading,
        state = formFields.get(Code),
        imeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = { submitClick.invoke() })
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        if (loadingRefresh != 0) {
            Text(
                color = MaterialThemeCustom.colors.textColorSecondary,
                style = MaterialTheme.typography.caption,
                text = stringResource(id = R.string.contact_change_common_code_time, loadingRefresh.toString()),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
            )
        } else {
            Button(
                enabled = !loading,
                onClick = {
                    onEvent(ContactChangePhoneCodeEvents.ContactChangePhoneCodeRefresh)
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.secondary),
            ) {
                Text(
                    text = stringResource(id = R.string.contact_change_common_code_btn).uppercase(),
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            delay(10)
            requesterField.requestFocus()
        }
    }
}

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
 
package com.keygenqt.demo_contacts.modules._common.ui.form.states

import android.content.Context
import android.util.Patterns.PHONE
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules._common.ui.form.base.FormFieldState
import com.keygenqt.demo_contacts.modules._common.ui.form.validation.getErrorIsBlank

class StatePhoneRequired : FormFieldState(checkValid = ::checkValid)

private fun checkValid(target: String) = listOfNotNull(
    getErrorIsBlank(target),
    getErrorIsNotPhone(target),
)

private fun getErrorIsNotPhone(target: String) = when {
    !PHONE.matcher(target).matches() -> { it: Context ->
        it.getString(R.string.is_incorrect)
    }
    else -> null
}
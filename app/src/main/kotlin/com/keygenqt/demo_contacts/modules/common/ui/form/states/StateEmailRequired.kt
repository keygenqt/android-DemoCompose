package com.keygenqt.demo_contacts.modules.common.ui.form.states

import android.content.Context
import android.util.Patterns.EMAIL_ADDRESS
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.common.ui.form.base.FormFieldState
import com.keygenqt.demo_contacts.modules.common.ui.form.validation.getErrorIsBlank

class EmailStateRequired : FormFieldState(checkValid = ::checkValid)

private fun checkValid(target: String) = listOfNotNull(
    getErrorIsBlank(target),
    getErrorIsNotEmail(target),
)

private fun getErrorIsNotEmail(target: String) = when {
    !EMAIL_ADDRESS.matcher(target).matches() -> { it: Context ->
        it.getString(R.string.is_incorrect)
    }
    else -> null
}
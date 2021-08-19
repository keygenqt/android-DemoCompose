package com.keygenqt.demo_contacts.modules.other.ui.form.states

import com.keygenqt.demo_contacts.modules.common.ui.form.base.FormFieldState
import com.keygenqt.demo_contacts.modules.common.ui.form.base.FormStates
import com.keygenqt.demo_contacts.modules.common.ui.form.states.EmailStateRequired
import com.keygenqt.demo_contacts.modules.common.ui.form.states.PasswordState

enum class SignInFormStates(val state: FormFieldState) : FormStates {
    SignInEmail(EmailStateRequired()),
    SignInPassword(PasswordState()),
}
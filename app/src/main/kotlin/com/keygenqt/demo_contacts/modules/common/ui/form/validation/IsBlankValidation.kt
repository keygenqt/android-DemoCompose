package com.keygenqt.demo_contacts.modules.common.ui.form.validation

import android.content.Context
import com.keygenqt.demo_contacts.R

fun getErrorIsBlank(target: String) =
    when {
        target.isBlank() -> { it: Context ->
            it.getString(R.string.is_required)
        }
        else -> null
    }

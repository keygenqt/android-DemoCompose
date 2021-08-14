package com.keygenqt.demo_contacts.modules.common.ui.form.base

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

open class FormFieldState(
    text: String = "",
    private val checkValid: (String) -> List<(Context) -> String> = { emptyList() },
) {

    private var _default = TextFieldValue(text = text)
    private var _text: TextFieldValue by mutableStateOf(_default)
    private var _errors: List<(Context) -> String> by mutableStateOf(listOf())

    var text: TextFieldValue
        get() = _text
        set(value) {
            _text = value
            _errors = checkValid(value.text)
        }

    val hasErrors: Boolean
        get() = _errors.isNotEmpty()

    fun validate() {
        _errors = checkValid(_text.text)
    }

    fun clearError() {
        _errors = emptyList()
    }

    fun getError(context: Context): String? {
        return _errors.firstOrNull()?.invoke(context)
    }

    fun getValue(): String {
        return _text.text
    }

    fun setError(error: (Context) -> String) {
        _errors = listOf(error)
    }

    fun positionToEnd() {
        _text = _text.copy(
            selection = TextRange(_text.text.length, _text.text.length)
        )
    }

    fun default(value: String): FormFieldState {
        _default = TextFieldValue(text = value)
        _text = _default
        return this
    }

    fun clear(): FormFieldState {
        _text = _default
        return this
    }
}

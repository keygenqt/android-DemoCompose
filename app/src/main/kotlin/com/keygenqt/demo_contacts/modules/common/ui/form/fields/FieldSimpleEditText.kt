package com.keygenqt.demo_contacts.modules.common.ui.form.fields

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.demo_contacts.modules.common.ui.compose.TextFieldError
import com.keygenqt.demo_contacts.modules.common.ui.form.base.FormFieldState
import com.keygenqt.demo_contacts.modules.common.ui.form.states.StateSimpleEditText
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalComposeUiApi
@Composable
fun FieldSimpleEditText(
    modifier: Modifier = Modifier,
    labelText: String = "Simple Edit Text",
    enabled: Boolean = true,
    state: FormFieldState = remember { StateSimpleEditText() },
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    TextField(
        maxLines = 1,
        singleLine = true,
        enabled = enabled,
        value = state.text,
        onValueChange = { state.text = it },
        label = { Text(labelText) },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    state.positionToEnd()
                }
            },
        textStyle = MaterialTheme.typography.body2,
        isError = state.hasErrors,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = keyboardActions
    )

    state.getError(LocalContext.current)?.let { error ->
        TextFieldError(textError = error)
    }
}


@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewFieldSimpleEditText() {
    MyTheme {
        Surface {
            FieldSimpleEditText()
        }
    }
}
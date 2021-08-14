package com.keygenqt.demo_contacts.modules.common.ui.form.fields

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.common.ui.compose.TextFieldError
import com.keygenqt.demo_contacts.modules.common.ui.form.base.FormFieldState
import com.keygenqt.demo_contacts.modules.common.ui.form.states.PasswordState
import com.keygenqt.demo_contacts.theme.MyTheme

@Composable
fun FieldPassword(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    state: FormFieldState = remember { PasswordState() },
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    var visibility: Boolean by remember { mutableStateOf(false) }
    TextField(
        enabled = enabled,
        value = state.text,
        onValueChange = { state.text = it },
        label = { Text(stringResource(id = R.string.form_password)) },
        visualTransformation = if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { visibility = !visibility }) {
                Icon(
                    imageVector = if (visibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = ""
                )
            }
        },
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
fun PreviewFieldPassword() {
    MyTheme {
        Surface {
            FieldPassword()
        }
    }
}
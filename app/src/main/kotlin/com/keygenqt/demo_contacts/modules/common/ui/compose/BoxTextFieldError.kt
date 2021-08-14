package com.keygenqt.demo_contacts.modules.common.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keygenqt.demo_contacts.theme.MyTheme

@Composable
fun FormError(
    modifier: Modifier = Modifier,
    textError: String = "Box Text Field Error Preview",
) {
    Card(
        elevation = 4.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.error)
        ) {
            Text(
                text = textError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = LocalTextStyle.current.copy(
                    color = Color.White,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewFormError() {
    MyTheme {
        Surface {
            FormError()
        }
    }
}
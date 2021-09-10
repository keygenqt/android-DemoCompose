package com.keygenqt.demo_contacts.modules._common.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun TopBarContentTitle(text: String, textAlign: TextAlign = TextAlign.Left) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontSize = TextUnit.Unspecified,
        text = text,
        textAlign = textAlign,
        color = MaterialTheme.colors.onPrimary
    )
}
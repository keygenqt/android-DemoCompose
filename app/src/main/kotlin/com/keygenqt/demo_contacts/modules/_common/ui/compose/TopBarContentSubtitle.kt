package com.keygenqt.demo_contacts.modules._common.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom

@Composable
fun TopBarContentSubtitle(text: String) {
    Text(
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialThemeCustom.colors.textColorSecondary,
        text = text,
        style = MaterialTheme.typography.caption,
    )
}
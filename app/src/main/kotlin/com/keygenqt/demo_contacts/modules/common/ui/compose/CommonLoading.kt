package com.keygenqt.demo_contacts.modules.common.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.keygenqt.demo_contacts.extensions.visible
import com.keygenqt.demo_contacts.theme.MyTheme

@Composable
fun CommonLoading(
    visibility: Boolean,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .visible(visibility)
    ) {
        val (loading) = createRefs()
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                .background(Color.Transparent)
                .constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCommonLoading() {
    MyTheme {
        Surface {
            CommonLoading(true)
        }
    }
}
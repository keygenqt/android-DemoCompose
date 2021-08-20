package com.keygenqt.demo_contacts.modules.profile.ui.screens.profileScreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.theme.MyTheme
import timber.log.Timber

@Composable
fun ProfileListBodyItem(
    item: Map<String, Any>,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .clickable(onClick = item[keyAction] as () -> Unit)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .size(48.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = item[keyIcon] as Painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.Center)
                )
            }
            Column {
                Text(
                    color = MaterialTheme.colors.onBackground,
                    text = item[keyName] as String,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileListBodyItemPreview() {
    MyTheme {
        Surface {
            ProfileListBodyItem(
                mapOf(
                    keyIcon to painterResource(R.drawable.ic_profile_menu_list_orders),
                    keyName to stringResource(id = R.string.profile_menu_list_orders),
                    keyAction to {
                        Timber.e("Click")
                    },
                ),
            )
        }
    }
}
/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.keygenqt.demo_contacts.modules.favorite.ui.screens.listFavorite

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.favorite.data.mock.mockFavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.ui.events.FavoriteEvents
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import com.keygenqt.demo_contacts.theme.MyTheme
import com.keygenqt.modifier.sizeSmall

@Composable
fun FavoriteItemList(
    model: FavoriteModel,
    modifier: Modifier = Modifier,
    onEvent: (FavoriteEvents) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        elevation = 12.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable {

                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = rememberImagePainter(
                        data = model.image?.imageUrl,
                        builder = {
                            fallback(R.drawable.ic_common_default_image)
                            placeholder(R.drawable.ic_common_default_image)
                            error(R.drawable.ic_common_default_image)
                        }
                    ),
                    contentDescription = model.name,
                    modifier = Modifier
                        .size(64.dp, 64.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    if (!model.subtitle.isNullOrEmpty()) {

                        Spacer(modifier = Modifier.sizeSmall())

                        Text(
                            text = stringResource(id = R.string.catalog_list_item_subcategories, model.subtitle),
                            style = MaterialTheme.typography.caption,
                            color = MaterialThemeCustom.colors.textColorSecondary,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewItemUser() {
    MyTheme {
        Surface {
            FavoriteItemList(model = mockFavoriteModel())
        }
    }
}
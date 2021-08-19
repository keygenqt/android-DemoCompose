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
 
package com.keygenqt.demo_contacts.modules.brands.ui.screens.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents

@Composable
fun FeedItemBrand(
    index: Int = 0,
    model: FeedBrandModel,
    onEvent: (BrandsEvents) -> Unit = {},
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .size(if (index == 0) 118.dp else 100.dp, 100.dp)
            .padding(top = 8.dp, bottom = 8.dp, start = if (index == 0) 16.dp else 0.dp, end = 16.dp)
    ) {
        Box(modifier = Modifier
            .clickable(onClick = {

            })
        ) {
            Image(
                painter = rememberImagePainter(model.logo.logoUrl),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}
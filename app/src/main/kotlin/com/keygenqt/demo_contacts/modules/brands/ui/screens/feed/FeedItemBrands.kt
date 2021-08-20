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

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.ui.events.BrandsEvents
import com.keygenqt.demo_contacts.modules._common.ui.compose.ClickableTextAnimation

@Composable
fun FeedItemBrands(
    name: String,
    brands: List<FeedBrandModel> = listOf(),
    onEvent: (BrandsEvents) -> Unit = {},
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            textAlign = TextAlign.Center,
            text = name,
            style = MaterialTheme.typography.h5,
        )

        ClickableTextAnimation(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = stringResource(id = R.string.brands_btn_all)
        ) {
            Toast.makeText(context, R.string.common_coming_soon, Toast.LENGTH_SHORT).show()
        }
    }

    LazyRow {
        itemsIndexed(brands) { index, model ->
            FeedItemBrand(
                index = index,
                model = model,
                onEvent = onEvent,
            )
        }
    }
}
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
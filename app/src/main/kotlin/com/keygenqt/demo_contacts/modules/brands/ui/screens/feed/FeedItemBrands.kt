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
import com.keygenqt.demo_contacts.modules.common.ui.compose.components.ClickableTextAnimation

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
            style = MaterialTheme.typography.h6,
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
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
 
package com.keygenqt.demo_contacts.modules.favorite.data.mappers

import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteImageModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoritePriceModel
import com.keygenqt.demo_contacts.modules.favorite.data.responses.FavoritesResponse
import com.keygenqt.demo_contacts.utils.ConstantsApp.API_URL_BASE

fun FavoritesResponse.toModels(): List<FavoriteModel> {
    return entries.map { ent ->
        ent.product.let {
            FavoriteModel(
                id = it.code ?: "",
                name = it.name ?: "",
                subtitle = it.subtitle ?: "",
                image = it.listingImage?.let { icon ->
                    FavoriteImageModel(
                        imageFormat = icon.format ?: "",
                        imageUrl = icon.url?.let { link -> "${API_URL_BASE}/$link" } ?: "",
                    )
                },
                price = it.price?.let { price ->
                    FavoritePriceModel(
                        value = price.value ?: 0.0,
                        currencyIso = price.currencyIso ?: "RUB"
                    )
                },
            )
        }

    }
}
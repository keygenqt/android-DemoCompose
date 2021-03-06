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
 
package com.keygenqt.demo_contacts.modules.favorite.data.responses

import androidx.compose.runtime.Immutable

@Immutable
data class FavoritesResponse(
    val entries: List<Favorite2Response>,
)

@Immutable
data class Favorite2Response(
    val product: FavoriteResponse,
)

@Immutable
data class FavoriteResponse(
    val code: String?,
    val name: String?,
    val subtitle: String?,
    val price: FavoritePriceResponse?,
    val listingImage: FavoriteImageResponse?,
)

@Immutable
class FavoritePriceResponse(
    val value: Double?,
    val currencyIso: String?,
    val icon: FavoriteImageResponse?,
)

@Immutable
class FavoriteImageResponse(
    val format: String?,
    val url: String?,
)
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
 
package com.keygenqt.demo_contacts.modules.favorite.data.mock

import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoriteModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoritePriceImageModel
import com.keygenqt.demo_contacts.modules.favorite.data.models.FavoritePriceModel

fun mockFavoriteModel() = FavoriteModel(
    id = "id",
    name = "Favorite Product",
    description = "The best of the best product",
    price = FavoritePriceModel(
        value = 12.237486,
        priceGroupCode = "group",
        icon = FavoritePriceImageModel(
            format = "jpg",
            width = 1650,
            height = 1275,
            imageType = "type",
            url = "https://github.com/keygenqt/android-DemoContacts/raw/master/data/product.jpg",
        )
    ),
)
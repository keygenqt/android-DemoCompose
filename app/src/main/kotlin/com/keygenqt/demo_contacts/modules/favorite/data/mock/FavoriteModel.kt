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
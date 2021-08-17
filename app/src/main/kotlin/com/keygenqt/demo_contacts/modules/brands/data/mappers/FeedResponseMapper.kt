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

package com.keygenqt.demo_contacts.modules.brands.data.mappers

import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerImageModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel
import com.keygenqt.demo_contacts.modules.brands.data.responses.FeedResponse
import com.keygenqt.demo_contacts.utils.ConstantsApp
import com.keygenqt.demo_contacts.utils.ConstantsApp.API_URL
import java.util.*

fun FeedResponse.toModel(): FeedModel {
    val rootId = UUID.randomUUID().toString()
    return FeedModel(
        id = rootId,
        brandName = brands?.name ?: "",
        banners = banners?.map { banner ->
            FeedBannerModel(
                id = banner.id ?: UUID.randomUUID().toString(),
                ownerId = rootId,
                title = banner.title ?: "",
                image = banner.image?.let { icon ->
                    FeedBannerImageModel(
                        format = icon.format ?: "",
                        width = icon.width ?: 0,
                        height = icon.height ?: 0,
                        imageType = icon.imageType ?: "",
                        url = icon.url?.let { link -> "$API_URL$link" } ?: "",
                    )
                },
            )
        } ?: listOf(),
        brands = brands?.brands?.map { brand ->
            FeedBrandModel(
                id = brand.code ?: UUID.randomUUID().toString(),
                ownerId = rootId,
                description = brand.description ?: "",
                name = brand.name ?: "",
                url = brand.url ?: "",
            )
        } ?: listOf(),
    )
}
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
 
package com.keygenqt.demo_contacts.modules.brands.data.responses

import androidx.compose.runtime.Immutable

@Immutable
data class FeedResponse(
    val banners: List<BannerResponse>?,
    val brands: BrandsResponse?,
)

@Immutable
data class BannerResponse(
    val id: String?,
    val image: BannerImageResponse?,
    val title: String?,
    val expandData: ExpandDataResponse?,
)

@Immutable
class ExpandDataResponse(
    val productCode: String?,
    val name: String?,
    val categoryCode: String?,
    val landingUrl: String?,
    val brandCode: String?,
    val brandCategoryCode: String?,
    val promotionCode: String?,
)

@Immutable
class BannerImageResponse(
    val format: String?,
    val width: Int?,
    val height: Int?,
    val imageType: String?,
    val url: String?,
)

@Immutable
data class BrandsResponse(
    val brands: List<BrandResponse>?,
    val name: String?,
)

@Immutable
data class BrandResponse(
    val code: String?,
    val name: String?,
    val description: String?,
    val url: String?,
    val brandForMobile: BannerLogoResponse?,
)

@Immutable
class BannerLogoResponse(
    val url: String?,
)
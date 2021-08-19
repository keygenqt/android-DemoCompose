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
 
package com.keygenqt.demo_contacts.modules.catalog.services.api.impl

import androidx.annotation.IntRange
import com.keygenqt.demo_contacts.modules.catalog.data.responses.BrandsResponse
import com.keygenqt.demo_contacts.modules.catalog.data.responses.CategoryResponse
import com.keygenqt.demo_contacts.utils.ConstantsPaging
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGet {

    @GET("catalog")
    suspend fun getListCatalog(
        @Query("page")
        page: Int = 1,
        @IntRange(from = 1, to = ConstantsPaging.MAX_PAGE_SIZE.toLong())
        @Query("per-page")
        perPage: Int = ConstantsPaging.PER_PAGE,
    ): Response<List<CategoryResponse>>

    @GET("brands")
    suspend fun getListBrands(
        @Query("page")
        page: Int = 1,
        @IntRange(from = 1, to = ConstantsPaging.MAX_PAGE_SIZE.toLong())
        @Query("per-page")
        perPage: Int = ConstantsPaging.PER_PAGE,
    ): Response<List<BrandsResponse>>
}



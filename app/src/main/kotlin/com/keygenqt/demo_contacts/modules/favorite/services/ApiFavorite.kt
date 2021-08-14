package com.keygenqt.demo_contacts.modules.favorite.services

import androidx.annotation.IntRange
import com.keygenqt.demo_contacts.data.responses.ProductResponse
import com.keygenqt.demo_contacts.utils.ConstantsPaging
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFavorite {
    @GET("api/news-feed?expand=uploads")
    suspend fun getListFavorite(
        @Query("page")
        page: Int = 1,

        @IntRange(from = 1, to = ConstantsPaging.MAX_PAGE_SIZE.toLong())
        @Query("per-page")
        perPage: Int = ConstantsPaging.PAGE_LIMIT,
    ): Response<List<ProductResponse>>
}
package com.keygenqt.demo_contacts.modules.favorite.services

import com.keygenqt.demo_contacts.BuildConfig
import com.keygenqt.demo_contacts.base.ResponseResult
import com.keygenqt.demo_contacts.base.executeWithResponse
import com.keygenqt.demo_contacts.data.mappers.toModels
import com.keygenqt.demo_contacts.data.models.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiServiceFavorite @Inject constructor(
    private val api: ApiFavorite,
) {
    suspend fun getListFavorite(page: Int): ResponseResult<List<ProductModel>> {
        return withContext(Dispatchers.IO) {
            if (BuildConfig.DEBUG) delay(1000L) // Simulate slow internet
            executeWithResponse {
                api.getListFavorite(page)
                    .body()
                    ?.toModels()
                    ?: emptyList()
            }
        }
    }
}
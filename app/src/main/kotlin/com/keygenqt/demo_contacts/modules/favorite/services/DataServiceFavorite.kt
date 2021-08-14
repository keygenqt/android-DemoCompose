package com.keygenqt.demo_contacts.modules.favorite.services

import androidx.paging.PagingSource
import com.keygenqt.demo_contacts.base.AppSharedPreferences
import com.keygenqt.demo_contacts.base.BaseDataService
import com.keygenqt.demo_contacts.data.AppDatabase
import com.keygenqt.demo_contacts.data.models.ProductModel
import kotlinx.coroutines.flow.Flow

class DataServiceFavorite(
    override val db: AppDatabase,
    override val preferences: AppSharedPreferences,
) : BaseDataService<DataServiceFavorite> {

    private val daoFavoriteModel = db.daoFavorite()

    fun pagingListFavorite(): PagingSource<Int, ProductModel> {
        return daoFavoriteModel.pagingSource()
    }

    suspend fun insertFavorite(models: List<ProductModel>) {
        daoFavoriteModel.insertModels(models)
    }

    suspend fun clearFavorite() {
        daoFavoriteModel.clear()
    }

    fun getFavorite(id: Int): Flow<ProductModel> {
        return daoFavoriteModel.getModel(id)
    }

    suspend fun updateFavorite(models: List<ProductModel>) {
        daoFavoriteModel.clear()
        daoFavoriteModel.insertModels(models)
    }
}
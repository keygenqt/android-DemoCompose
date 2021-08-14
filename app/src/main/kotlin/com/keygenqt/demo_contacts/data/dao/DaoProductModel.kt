package com.keygenqt.demo_contacts.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keygenqt.demo_contacts.data.models.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoProductModel {
    @Query("SELECT * FROM ProductModel")
    fun pagingSource(): PagingSource<Int, ProductModel>

    @Query("SELECT * FROM ProductModel WHERE id = :id")
    fun getModel(id: Int): Flow<ProductModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(models: List<ProductModel>)

    @Query("DELETE FROM ProductModel")
    suspend fun clear()
}

package com.keygenqt.demo_contacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keygenqt.demo_contacts.data.dao.DaoProductModel
import com.keygenqt.demo_contacts.data.models.ProductModel

@Database(
    entities = [
        ProductModel::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoFavorite(): DaoProductModel
}

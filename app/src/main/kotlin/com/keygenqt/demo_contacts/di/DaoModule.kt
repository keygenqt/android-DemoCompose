package com.keygenqt.demo_contacts.di

import com.keygenqt.demo_contacts.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideDaoProducts(appDatabase: AppDatabase) = appDatabase.daoFavorite()
}

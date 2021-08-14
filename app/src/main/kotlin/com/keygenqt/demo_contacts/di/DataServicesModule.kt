package com.keygenqt.demo_contacts.di

import com.keygenqt.demo_contacts.base.AppSharedPreferences
import com.keygenqt.demo_contacts.data.AppDatabase
import com.keygenqt.demo_contacts.modules.favorite.services.DataServiceFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataServicesModule {

    @Provides
    fun provideDataServiceChat(db: AppDatabase, preferences: AppSharedPreferences) =
        DataServiceFavorite(db, preferences)
}

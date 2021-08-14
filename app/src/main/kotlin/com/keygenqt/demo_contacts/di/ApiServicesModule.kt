package com.keygenqt.demo_contacts.di

import com.keygenqt.demo_contacts.modules.favorite.services.ApiFavorite
import com.keygenqt.demo_contacts.modules.favorite.services.ApiServiceFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ApiServicesModule {

    @Provides
    fun provideServiceChat(api: ApiFavorite) = ApiServiceFavorite(api)
}

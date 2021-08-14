package com.keygenqt.demo_contacts.di

import com.keygenqt.demo_contacts.modules.favorite.services.ApiFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiDeclarationModule {

    @Provides
    fun provideApiChat(retrofit: Retrofit) = retrofit.create(ApiFavorite::class.java)
}

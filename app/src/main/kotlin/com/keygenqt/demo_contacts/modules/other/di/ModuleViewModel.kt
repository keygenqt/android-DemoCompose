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
 
package com.keygenqt.demo_contacts.modules.other.di

import com.keygenqt.demo_contacts.base.AppDatabase
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.modules.other.services.api.ApiOther
import com.keygenqt.demo_contacts.modules.other.services.apiService.ApiServiceOther
import com.keygenqt.demo_contacts.modules.other.services.data.DataServiceOther
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ModuleViewModel {

    @Provides
    fun provideDataServiceOther(db: AppDatabase, preferences: AppPreferences) =
        DataServiceOther(db, preferences)

    @Provides
    fun provideApiServiceOther(api: ApiOther) = ApiServiceOther(api)
}

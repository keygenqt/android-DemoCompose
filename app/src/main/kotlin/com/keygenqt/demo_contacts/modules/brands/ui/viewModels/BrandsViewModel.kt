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

package com.keygenqt.demo_contacts.modules.brands.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.demo_contacts.base.error
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.modules.brands.data.relations.FeedRelation
import com.keygenqt.demo_contacts.modules.brands.services.apiService.ApiServiceBrands
import com.keygenqt.demo_contacts.modules.brands.services.data.DataServiceBrands
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val data: DataServiceBrands,
    apiService: ApiServiceBrands,
) : ViewModel() {

    init {
        viewModelScope.launch {
            apiService.getFeed()
                .success { response ->
                    // clear old data
                    data.clear()
                    // insert if not null
                    response?.let {
                        data.insert(it)
                    }
                }
                .error {
                    Timber.e(it)
                }
        }
    }

    fun getFeed(): Flow<FeedRelation?> {
        return data.getFeedRelation().distinctUntilChanged()
    }

}

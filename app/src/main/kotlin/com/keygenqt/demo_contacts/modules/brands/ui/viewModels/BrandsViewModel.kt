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
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.keygenqt.demo_contacts.base.done
import com.keygenqt.demo_contacts.base.error
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.modules.brands.data.relations.FeedRelation
import com.keygenqt.demo_contacts.modules.brands.services.apiService.ApiServiceBrands
import com.keygenqt.demo_contacts.modules.brands.services.data.DataServiceBrands
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val data: DataServiceBrands,
    private val apiService: ApiServiceBrands,
    private val crashlytics: FirebaseCrashlytics,
) : ViewModel() {

    private val _commonError: MutableStateFlow<String?> = MutableStateFlow(null)
    val commonError: StateFlow<String?> get() = _commonError.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    init {
        updateFeed()
    }

    fun updateFeed() {
        // start update
        _commonError.value = null
        _loading.value = true
        // make a request
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
                    crashlytics.recordException(it)
                    _commonError.value = it.message ?: "Error update feed"
                }
                .done {
                    delay(500) // disable loading after insert
                    _loading.value = false
                }
        }
    }

    fun getFeed(): Flow<FeedRelation?> {
        return data.getFeedRelation().distinctUntilChanged()
    }

}

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

package com.keygenqt.demo_contacts.modules.profile.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.keygenqt.demo_contacts.base.done
import com.keygenqt.demo_contacts.base.error
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactsModel
import com.keygenqt.demo_contacts.modules.profile.services.apiService.ApiServiceProfile
import com.keygenqt.demo_contacts.modules.profile.services.data.DataServiceProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileContactsViewModel @Inject constructor(
    private val data: DataServiceProfile,
    private val apiService: ApiServiceProfile,
    private val crashlytics: FirebaseCrashlytics,
) : ViewModel() {

    private val _loadingActionBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingActionBar: StateFlow<Boolean> get() = _loadingActionBar.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    init {
        userUpdateContacts()
    }

    fun updateStatusSmall(statusEmail: Boolean, statusPhone: Boolean) {
        _loadingActionBar.value = true
        viewModelScope.launch {
            data.getUserContacts()?.let { model ->

                val newModel = model.copy(
                    phone = model.phone.copy(notifySmsShort = statusPhone),
                    email = model.email.copy(notifyMailShort = statusEmail)
                )

                apiService.updateUserContacts(newModel)
                    .success { response ->
                        if (response) {
                            data.updateUserContacts(newModel)
                        }
                    }
                    .error {
                        Timber.e(it)
                        crashlytics.recordException(it)
                    }
                    .done {
                        delay(500) // disable loading after insert
                        _loading.value = false
                    }
            }
            _loadingActionBar.value = false
        }
    }

    fun updateStatusFull(statusEmail: Boolean, statusPhone: Boolean) {
        _loadingActionBar.value = true
        viewModelScope.launch {
            data.getUserContacts()?.let { model ->

                val newModel = model.copy(
                    phone = model.phone.copy(notifySmsFull = statusPhone),
                    email = model.email.copy(notifyMailFull = statusEmail)
                )

                apiService.updateUserContacts(newModel)
                    .success { response ->
                        if (response) {
                            data.updateUserContacts(newModel)
                        }
                    }
                    .error {
                        crashlytics.recordException(it)
                    }
                    .done {
                        delay(500) // disable loading after insert
                        _loading.value = false
                    }
            }
            _loadingActionBar.value = false
        }
    }

    fun userUpdateContacts() {
        _loading.value = true
        viewModelScope.launch {
            apiService.getUserContacts()
                .success { response ->
                    data.updateUserContacts(response)
                }
                .error {
                    Timber.e(it)
                    crashlytics.recordException(it)
                }
                .done {
                    delay(500) // disable loading after insert
                    _loading.value = false
                }
        }
    }

    fun getUserContacts(): Flow<UserContactsModel?> {
        return data.getUserContactsFlow().distinctUntilChanged()
    }
}

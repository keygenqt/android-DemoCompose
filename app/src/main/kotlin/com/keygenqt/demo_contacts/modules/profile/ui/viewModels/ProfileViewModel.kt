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
import com.keygenqt.demo_contacts.base.errorUnknownHost
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.modules.profile.data.models.UserModel
import com.keygenqt.demo_contacts.modules.profile.services.apiService.ApiServiceProfile
import com.keygenqt.demo_contacts.modules.profile.services.data.DataServiceProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val data: DataServiceProfile,
    private val apiService: ApiServiceProfile,
    private val crashlytics: FirebaseCrashlytics,
) : ViewModel() {

    private val _errorConnection: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorConnection: StateFlow<Boolean> get() = _errorConnection.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    fun userUpdate() {
        _loading.value = true
        viewModelScope.launch {
            apiService.getUser()
                .success { response ->
                    data.updateUser(response)
                }
                .error {
                    Timber.e(it)
                    crashlytics.recordException(it)
                }
                .done {
                    delay(500) // disable loading after insert
                    _loading.value = false
                    _errorConnection.value = false
                }
                .errorUnknownHost {
                    _errorConnection.value = true
                }
        }
    }

    fun getUser(): Flow<UserModel?> {
        return data.getUser().distinctUntilChanged()
    }
}

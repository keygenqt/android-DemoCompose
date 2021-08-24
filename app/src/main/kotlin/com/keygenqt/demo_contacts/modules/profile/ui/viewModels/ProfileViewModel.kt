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
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.extensions.timer
import com.keygenqt.demo_contacts.modules.profile.data.models.UserModel
import com.keygenqt.demo_contacts.modules.profile.services.apiService.ApiServiceProfile
import com.keygenqt.demo_contacts.modules.profile.services.data.DataServiceProfile
import com.keygenqt.demo_contacts.utils.ConstantsApp.REFRESH_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
    private val preferences: AppPreferences,
) : ViewModel() {

    private var timerJob: Job? = null

    private val _loadingRefresh: MutableStateFlow<Int> = MutableStateFlow(0)
    val loadingRefresh: StateFlow<Int> get() = _loadingRefresh.asStateFlow()

    private val _commonError: MutableStateFlow<String?> = MutableStateFlow(null)
    val commonError: StateFlow<String?> get() = _commonError.asStateFlow()

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
                    _commonError.value = it.message ?: "Error update feed"
                }
                .done {
                    delay(500) // disable loading after insert
                    _loading.value = false
                }
        }
    }

    fun getUser(): Flow<UserModel?> {
        return data.getUser().distinctUntilChanged()
    }

    fun refreshCode() {
        _commonError.value = null
        _loading.value = true

        viewModelScope.launch {
            // Simulate a request
            delay(2000L)
            _loading.value = false
            runTimer()
        }
    }

    fun runTimer() {
        timerJob?.cancel()
        timerJob = timer(REFRESH_DELAY) { second ->
            _loadingRefresh.value = second
        }
    }

    fun changeEmail(
        email: String,
        success: () -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true

        viewModelScope.launch {
            // Simulate a request
            delay(2000L)
            _loading.value = false
            success.invoke()
        }
    }

    fun changeEmailCode(
        code: String,
        success: () -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true

        viewModelScope.launch {
            // Simulate a request
            delay(2000L)
            _loading.value = false
            success.invoke()

            // cancel timer
            timerJob?.cancel()
        }
    }

    fun changePhone(
        email: String,
        success: () -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true

        viewModelScope.launch {
            // Simulate a request
            delay(2000L)
            _loading.value = false
            success.invoke()
        }
    }

    fun changePhoneCode(
        code: String,
        success: () -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true

        viewModelScope.launch {
            // Simulate a request
            delay(2000L)
            _loading.value = false
            success.invoke()

            // cancel timer
            timerJob?.cancel()
        }
    }
}

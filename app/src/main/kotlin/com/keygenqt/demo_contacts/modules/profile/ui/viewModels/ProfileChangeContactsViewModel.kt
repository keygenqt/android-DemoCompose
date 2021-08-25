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
import com.keygenqt.demo_contacts.extensions.timer
import com.keygenqt.demo_contacts.modules.profile.services.apiService.ApiServiceProfile
import com.keygenqt.demo_contacts.modules.profile.services.data.DataServiceProfile
import com.keygenqt.demo_contacts.utils.ConstantsApp.REFRESH_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileChangeContactsViewModel @Inject constructor(
    private val data: DataServiceProfile,
    private val apiService: ApiServiceProfile,
    private val crashlytics: FirebaseCrashlytics,
) : ViewModel() {

    private var timerJob: Job? = null

    private val _loadingRefresh: MutableStateFlow<Int> = MutableStateFlow(0)
    val loadingRefresh: StateFlow<Int> get() = _loadingRefresh.asStateFlow()

    private val _commonError: MutableStateFlow<String?> = MutableStateFlow(null)
    val commonError: StateFlow<String?> get() = _commonError.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    fun runTimer() {
        timerJob?.cancel()
        timerJob = timer(REFRESH_DELAY) { second ->
            _loadingRefresh.value = second
        }
    }

    fun changeEmailOrPhone(
        emailOrPhone: String,
        success: () -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true
        viewModelScope.launch {
            apiService.sendCode(emailOrPhone)
                .success { response ->
                    if (response) {
                        success.invoke()
                    }
                }
                .error {
                    Timber.e(it)
                    crashlytics.recordException(it)
                    _commonError.value = it.message ?: "Change phone failed";
                }
                .done {
                    delay(500) // disable loading after insert
                    _loading.value = false
                }
        }
    }

    fun checkEmailOrPhone(
        emailOrPhone: String,
        code: String,
        success: () -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true
        viewModelScope.launch {
            apiService.checkCode(emailOrPhone, code)
                .success { response ->
                    // @todo api response always false - demo fix
                    when ((0..1).random()) {
                        0 -> success.invoke()
                        1 -> _commonError.value = "Check code failed"
                    }
                }
                .error {
                    Timber.e(it)
                    crashlytics.recordException(it)
                    _commonError.value = it.message ?: "Check code failed"
                }
                .done {
                    delay(500) // disable loading after insert
                    _loading.value = false
                }
        }
    }
}

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
 
package com.keygenqt.demo_contacts.modules.other.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.demo_contacts.base.done
import com.keygenqt.demo_contacts.base.error
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.modules.other.services.apiService.ApiServiceOther
import com.keygenqt.demo_contacts.modules.other.services.data.DataServiceOther
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val data: DataServiceOther,
    private val apiService: ApiServiceOther,
) : ViewModel() {

    private val _commonError: MutableStateFlow<String?> = MutableStateFlow(null)
    val commonError: StateFlow<String?> get() = _commonError.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    fun signIn(
        login: String,
        passw: String,
        success: (accessToken: String, refreshToken: String) -> Unit,
    ) {
        _commonError.value = null
        _loading.value = true
        viewModelScope.launch {
            apiService.signIn(login = login, passw = passw)
                .done { _loading.value = false }
                .success {
                    success.invoke(it.tokenPair.accessToken.value, it.tokenPair.refreshToken.value)
                }
                .error { _commonError.value = it.message ?: "Authentication failed"; Timber.e(it) }
        }
    }
}

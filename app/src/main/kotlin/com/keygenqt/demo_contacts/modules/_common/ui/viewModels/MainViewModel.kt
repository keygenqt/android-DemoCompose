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
 
package com.keygenqt.demo_contacts.modules._common.ui.viewModels

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.keygenqt.demo_contacts.base.preferences.AppPreferences
import com.keygenqt.demo_contacts.modules._common.services.DataServiceCommon
import com.keygenqt.demo_contacts.modules.brands.navigation.nav.BrandsNav
import com.keygenqt.demo_contacts.modules.other.navigation.nav.OtherNav
import com.keygenqt.response.LocalTryExecuteWithResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val data: DataServiceCommon,
    private val analytics: FirebaseAnalytics,
    private val preferences: AppPreferences,
) : ViewModel() {

    private val _toggleRefresh: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val toggleRefresh: StateFlow<Boolean> get() = _toggleRefresh.asStateFlow()

    private val _isReady: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> get() = _isReady.asStateFlow()

    private val _isLogin: MutableStateFlow<Boolean> = MutableStateFlow(preferences.isLogin())
    val isLogin: StateFlow<Boolean> get() = _isLogin.asStateFlow()

    private val _showSnackBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showSnackBar: StateFlow<Boolean> get() = _showSnackBar.asStateFlow()

    private val _route: MutableStateFlow<String> = MutableStateFlow("")
    val route: StateFlow<String> get() = _route.asStateFlow()

    init {
        viewModelScope.launch {
            // Hold a little splash
            delay(500)

            // Show token
            Timber.d("User token -> ${preferences.accessToken}")

            // Start app
            _isReady.value = true
        }
    }

    fun toggleSnackBar() {
        _showSnackBar.value = true
        viewModelScope.launch {
            delay(1500) // Loading second click
            _showSnackBar.value = false
        }
    }

    fun listRefresh() {
        _toggleRefresh.value = true
        viewModelScope.launch {
            delay(1000)
            _toggleRefresh.value = false
        }
    }

    fun setCurrentRoute(route: String) {
        if (route != _route.value) {
            // update data
            _route.value = route
            // sand analytics
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, route)
            })
        }
    }

    fun getStartRoute(): String {
        return if (preferences.isStartPage) {
            OtherNav.OnboardingNav.OnboardingScreen.route
        } else {
            BrandsNav.MainNav.FeedScreen.route
        }
    }

    fun startPageCompleted() {
        preferences.isStartPage = false
    }

    fun startUser(accessToken: String, refreshToken: String) {
        _isLogin.value = true
        // update credentials
        preferences.setTokens(accessToken, refreshToken)
    }

    fun logout() {
        _isLogin.value = false
        // clear preferences
        preferences.clearAfterLogout()
        // clear preferences
        viewModelScope.launch {
            data.clearAfterLogout()
        }
    }
}

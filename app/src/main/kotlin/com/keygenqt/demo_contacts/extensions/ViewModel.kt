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
 
package com.keygenqt.demo_contacts.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.demo_contacts.utils.ConstantsApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlin.math.roundToLong


fun ViewModel.timer(
    timestamp: Long = ConstantsApp.REFRESH_DELAY,
    second: (Int) -> Unit,
) = flow {
    val stop = System.currentTimeMillis() + timestamp
    second.invoke((ConstantsApp.REFRESH_DELAY / 1000).toInt())
    while (stop > System.currentTimeMillis()) {
        emit(Unit)
        delay(1000L) // one sec
        val value = (((stop - System.currentTimeMillis()).toDouble() / 1000.00).roundToLong()).toInt()
        second.invoke(value)
    }
}.launchIn(viewModelScope)
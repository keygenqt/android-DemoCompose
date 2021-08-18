package com.keygenqt.demo_contacts.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.keygenqt.demo_contacts.modules.common.ui.viewModels.MainViewModel

// Update after the second click on the active bottom navigator
@Composable
fun MainViewModel.ListenRefresh(listener: () -> Unit) {
    val refresh by toggleRefresh.collectAsState()
    LaunchedEffect(refresh) {
        if (refresh) {
            listener.invoke()
        }
    }
}
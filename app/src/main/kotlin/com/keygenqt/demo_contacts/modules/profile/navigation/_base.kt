package com.keygenqt.demo_contacts.modules.profile.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import com.keygenqt.demo_contacts.modules._common.navigation.NavActions
import com.keygenqt.demo_contacts.modules._common.ui.viewModels.MainViewModel

@ExperimentalComposeUiApi
@Suppress("UNUSED_PARAMETER")
fun NavGraphBuilder.profileNavGraph(
    navActions: NavActions,
    baseViewModel: MainViewModel,
) {
    indexNavGraph(
        navActions = navActions,
        baseViewModel = baseViewModel,
    )
    contactsNavGraph(
        navActions = navActions,
    )
}
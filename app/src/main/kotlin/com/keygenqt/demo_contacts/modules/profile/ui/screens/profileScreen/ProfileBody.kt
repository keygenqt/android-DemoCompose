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
 
package com.keygenqt.demo_contacts.modules.profile.ui.screens.profileScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.base.LocalBaseViewModel
import com.keygenqt.demo_contacts.extensions.ListenRefresh
import com.keygenqt.demo_contacts.extensions.toColor
import com.keygenqt.demo_contacts.modules._common.navigation.NavScreen
import com.keygenqt.demo_contacts.modules._common.ui.compose.MainScaffold
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun ProfileBody(
    isLogin: Boolean,
    onEvent: (ProfileEvents) -> Unit = {},
) {

    val scope = rememberCoroutineScope()
    var refresh: Boolean by remember { mutableStateOf(false) }

    val refreshAction = {
        refresh = true
        scope.launch {
            delay(1000L)
            refresh = false
        }
    }

    LocalBaseViewModel.current.ListenRefresh {
        if (it == NavScreen.ProfileScreen.route) refreshAction.invoke()
    }

    MainScaffold(
        title = stringResource(id = R.string.profile_title)
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(refresh),
            onRefresh = {
                refreshAction.invoke()
            },
            indicator = { st, tr ->
                SwipeRefreshIndicator(
                    state = st,
                    refreshTriggerDistance = tr,
                    contentColor = MaterialTheme.colors.onPrimary,
                )
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colors.background)
                    .fillMaxSize()
            ) {

                if (isLogin) {
                    ProfileAddCardBody(
                        onEvent = onEvent
                    )
                } else {
                    ProfileLoginBody(
                        onEvent = onEvent
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))

                if (isLogin) {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = 12.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable(onClick = {

                            })
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_common_default_image_user),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .size(48.dp)
                            )
                            Column {
                                Text(
                                    color = MaterialTheme.colors.onBackground,
                                    text = stringResource(id = R.string.profile_details),
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp)
                                )
                            }
                        }
                    }
                } else {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = 12.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable(onClick = {

                            })
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .size(48.dp)
                                    .background("#F01E00".toColor())
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_profile_my_card),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .align(Alignment.Center)
                                )
                            }
                            Column {
                                Text(
                                    color = MaterialTheme.colors.onBackground,
                                    text = stringResource(id = R.string.profile_card),
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(4.dp))

                ProfileListBody(
                    isLogin = isLogin,
                    onEvent = onEvent
                )
            }
        }
    }
}




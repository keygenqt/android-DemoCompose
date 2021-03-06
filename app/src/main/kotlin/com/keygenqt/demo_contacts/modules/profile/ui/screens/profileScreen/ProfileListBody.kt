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

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.profile.ui.events.ProfileEvents
import com.keygenqt.demo_contacts.theme.MyTheme
import timber.log.Timber

const val keyIcon = "keyIcon"
const val keyName = "keyName"
const val keyAction = "keyAction"

@Composable
fun ProfileListBody(
    isLogin: Boolean,
    onEvent: (ProfileEvents) -> Unit = {},
) {
    Column {
        mutableListOf<Map<String, Any>>().apply {
            add(
                mapOf(
                    keyIcon to painterResource(R.drawable.ic_profile_menu_list_loyalty),
                    keyName to stringResource(id = R.string.profile_menu_list_loyalty),
                    keyAction to {
                        Timber.e("Click")
                    },
                ),
            )
            if (isLogin) {
                add(
                    mapOf(
                        keyIcon to painterResource(R.drawable.ic_profile_menu_list_contacts),
                        keyName to stringResource(id = R.string.profile_menu_list_contacts),
                        keyAction to {
                            onEvent(ProfileEvents.NavigateToContactSettings)
                        },
                    ),
                )
            }
            if (isLogin) {
                add(
                    mapOf(
                        keyIcon to painterResource(R.drawable.ic_profile_menu_list_orders),
                        keyName to stringResource(id = R.string.profile_menu_list_orders),
                        keyAction to {
                            Timber.e("Click")
                        },
                    ),
                )
            }
            if (isLogin) {
                add(
                    mapOf(
                        keyIcon to painterResource(R.drawable.ic_profile_menu_list_purchased),
                        keyName to stringResource(id = R.string.profile_menu_list_purchased),
                        keyAction to {
                            Timber.e("Click")
                        },
                    ),
                )
            }
            add(
                mapOf(
                    keyIcon to painterResource(R.drawable.ic_profile_menu_list_questions),
                    keyName to stringResource(id = R.string.profile_menu_list_questions),
                    keyAction to {
                        Timber.e("Click")
                    },
                ),
            )
            add(
                mapOf(
                    keyIcon to painterResource(R.drawable.ic_profile_menu_list_contact_us),
                    keyName to stringResource(id = R.string.profile_menu_list_contact_us),
                    keyAction to {
                        Timber.e("Click")
                    },
                ),
            )
            if (isLogin) {
                add(
                    mapOf(
                        keyIcon to painterResource(R.drawable.ic_profile_menu_list_logout),
                        keyName to stringResource(id = R.string.profile_menu_list_logout),
                        keyAction to {
                            onEvent(ProfileEvents.NavigateLogout)
                        },
                    ),
                )
            }
        }.forEach { item ->
            ProfileListBodyItem(item = item)
        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileListBodyPreview() {
    MyTheme {
        Surface {
            ProfileListBody(true)
        }
    }
}
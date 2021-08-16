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
 
package com.keygenqt.demo_contacts.modules.common.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.keygenqt.demo_contacts.modules.common.navigation.HomeTab
import com.keygenqt.demo_contacts.modules.common.navigation.NavActions
import com.keygenqt.demo_contacts.modules.common.navigation.NavScreen
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import com.keygenqt.demo_contacts.theme.MyTheme

@Composable
fun BottomBar(
    navActions: NavActions,
    currentRoute: String = NavScreen.BrandsScreen.route,
) {
    if (HomeTab.values().any { it.route == currentRoute }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BottomNavigation(
                modifier = Modifier.navigationBarsHeight(56.dp)
            ) {
                HomeTab.values().forEach { tab ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null,
                                tint = with(MaterialThemeCustom.colors) { if (tab.route == currentRoute) tabEnable else tabDisable }
                            )
                        },
                        selected = tab.route == currentRoute,
                        onClick = {
                            when (tab) {
                                HomeTab.BRANDS -> navActions.navigateToBrands.invoke()
                                HomeTab.CATALOG -> navActions.navigateToCatalog.invoke()
                                HomeTab.PROFILE -> navActions.navigateToProfile.invoke()
                                HomeTab.FAVORITE -> navActions.navigateToFavorite.invoke()
                                HomeTab.CART -> navActions.navigateToCart.invoke()
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White,
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    }
}

@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomBarPreview() {
    MyTheme {
        Surface {
            BottomBar(navActions = NavActions(rememberNavController()))
        }
    }
}
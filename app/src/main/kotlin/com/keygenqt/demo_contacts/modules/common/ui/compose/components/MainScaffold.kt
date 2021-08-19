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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.keygenqt.demo_contacts.R
import com.keygenqt.demo_contacts.modules.common.ui.form.states.StateSimpleEditText
import com.keygenqt.demo_contacts.theme.MaterialThemeCustom
import com.keygenqt.demo_contacts.theme.MyTheme

@ExperimentalComposeUiApi
@Composable
fun MainScaffold(
    title: String? = null,
    subTitle: String? = null,
    isLoaderShow: Boolean = false,
    icon: ImageVector? = null,
    navigationIconOnClick: () -> Unit = {},
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentDescription: String = stringResource(R.string.common_navigate_up),
    floatingActionButtonOnClick: () -> Unit = {},
    searchListener: ((String?) -> Unit)? = null,
    contentFloatingActionButton: @Composable (() -> Unit)? = null,
    actions: @Composable ((RowScope) -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {

    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    var isShowSearch by remember { mutableStateOf(false) }
    val state = remember { StateSimpleEditText() }
    val requester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = title?.let {
            {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = elevation,
                    title = {
                        Box {
                            searchListener?.let {
                                if (isShowSearch) {
                                    if (state.getValue().isEmpty()) {
                                        Text(
                                            fontSize = TextUnit.Unspecified,
                                            text = stringResource(id = R.string.common_search),
                                            color = MaterialTheme.colors.onPrimary
                                        )
                                    }
                                    BasicTextField(
                                        singleLine = true,
                                        value = state.text,
                                        onValueChange = { state.text = it },
                                        modifier = Modifier
                                            .focusRequester(requester)
                                            .fillMaxWidth()
                                            .onFocusChanged { focusState ->
                                                if (focusState.isFocused) {
                                                    state.positionToEnd()
                                                }
                                            },
                                        textStyle = MaterialTheme.typography.h6.merge(TextStyle(color = MaterialTheme.colors.onPrimary)),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            capitalization = KeyboardCapitalization.Sentences,
                                            imeAction = ImeAction.Search
                                        ),
                                        keyboardActions = KeyboardActions(onSearch = {
                                            focusManager.clearFocus()
                                            searchListener(state.getValue())
                                            softwareKeyboardController?.hide()
                                        }),
                                        cursorBrush = SolidColor(MaterialTheme.colors.onPrimary)
                                    )
                                    LaunchedEffect(isShowSearch) {
                                        requester.requestFocus()
                                    }
                                } else {
                                    Text(
                                        fontSize = TextUnit.Unspecified,
                                        text = title,
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                }
                            } ?: run {
                                Column(
                                    modifier = Modifier
                                        .padding(end = 12.dp)
                                        .fillMaxWidth(),
                                ) {

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = title,
                                        style = MaterialTheme.typography.h6,
                                    )

                                    subTitle?.let {

                                        Spacer(modifier = Modifier.size(2.dp))

                                        Text(
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center,
                                            color = MaterialThemeCustom.colors.textColorSecondary,
                                            text = subTitle,
                                            style = MaterialTheme.typography.subtitle2,
                                        )
                                    }
                                }
                            }
                        }
                    },
                    navigationIcon = icon?.let {
                        {
                            IconButton(onClick = navigationIconOnClick) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = contentDescription,
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                    },
                    actions = {
                        searchListener?.let {
                            IconButton(onClick = {
                                state.clear()
                                isShowSearch = !isShowSearch
                                if (!isShowSearch) {
                                    searchListener(null)
                                    softwareKeyboardController?.hide()
                                    requester.freeFocus()
                                }
                            }) {
                                if (isShowSearch) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Search",
                                        tint = MaterialTheme.colors.onPrimary
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = MaterialTheme.colors.onPrimary
                                    )
                                }
                            }
                        }
                        actions?.invoke(this)
                        if (isLoaderShow) {
                            Loader()
                        }
                    }
                )
            }
        } ?: {},
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                content.invoke(it)
                contentFloatingActionButton?.let {
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomEnd),
                        onClick = floatingActionButtonOnClick
                    ) {
                        contentFloatingActionButton.invoke()
                    }
                }
            }
        },
    )
}

@ExperimentalComposeUiApi
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScaffold() {
    MyTheme {
        MainScaffold(
            title = "Title"
        ) {}
    }
}
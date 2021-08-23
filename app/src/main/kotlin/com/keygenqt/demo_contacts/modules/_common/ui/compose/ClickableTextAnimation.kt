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
 
package com.keygenqt.demo_contacts.modules._common.ui.compose

import androidx.compose.animation.Animatable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ClickableTextAnimation(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var click: Boolean by remember { mutableStateOf(false) }
    val textColorDefault = MaterialTheme.colors.onBackground
    val textColorAction = MaterialTheme.colors.onSurface
    val color = remember { Animatable(textColorDefault) }

    ClickableText(
        modifier = modifier,
        style = style,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = color.value,
                )
            ) {
                append(text)
            }
        },
    ) {
        click = true
        scope.launch {
            delay(500)
            click = false
        }
        onClick.invoke()
    }

    LaunchedEffect(click) {
        color.animateTo(if (click) textColorAction else textColorDefault)
    }
}
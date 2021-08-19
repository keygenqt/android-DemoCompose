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
 
package com.keygenqt.demo_contacts.modules.brands.data.models

import androidx.compose.runtime.Immutable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keygenqt.demo_contacts.base.interfaces.IModelRelation

@Entity
@Immutable
data class FeedBrandModel(
    @PrimaryKey
    override val id: String,
    override val ownerId: String,
    val description: String,
    val name: String,
    val url: String,
    @Embedded val logo: FeedBrandLogoModel,
) : IModelRelation
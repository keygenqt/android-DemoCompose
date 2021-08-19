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
 
package com.keygenqt.demo_contacts.modules.brands.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBannerModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedBrandModel
import com.keygenqt.demo_contacts.modules.brands.data.models.FeedModel

data class FeedRelation(
    @Embedded
    val owner: FeedModel,
    @Relation(parentColumn = "id", entityColumn = "ownerId")
    val banners: List<FeedBannerModel>,
    @Relation(parentColumn = "id", entityColumn = "ownerId")
    val brands: List<FeedBrandModel>,
)


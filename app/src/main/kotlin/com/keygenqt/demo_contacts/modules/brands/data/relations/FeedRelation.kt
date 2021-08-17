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


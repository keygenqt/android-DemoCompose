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
 
package com.keygenqt.demo_contacts.modules.profile.data.mock

import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactEmailModel
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactPhoneModel
import com.keygenqt.demo_contacts.modules.profile.data.models.UserContactsModel

fun mockUserContactsModel(): UserContactsModel {
    return UserContactsModel(
        id = "id",
        email = UserContactEmailModel(
            confirmedContactEmail = true,
            contactEmail = "email@gmail.com",
            notifyMailShort = true,
            notifyMailFull = true,
        ),
        phone = UserContactPhoneModel(
            confirmedContactPhone = true,
            contactPhone = "+ 7 123 121 12",
            notifySmsShort = true,
            notifySmsFull = true,
        ),
    )
}
package com.keygenqt.demo_contacts.extensions

import com.keygenqt.demo_contacts.base.HTTPResult

fun Int.toHTTPResult(): HTTPResult {
    return HTTPResult::class.sealedSubclasses
        .firstOrNull { it.objectInstance?.code == this }
        ?.objectInstance
        ?: HTTPResult.ResultUnknown()
}
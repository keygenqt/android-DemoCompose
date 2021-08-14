package com.keygenqt.demo_contacts.extensions

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

fun Exception?.logging(defaultMessage: String): String {
    return this?.let { ex ->
        FirebaseCrashlytics.getInstance().recordException(ex)
        Timber.e(ex)
        ex.message
    } ?: defaultMessage
}
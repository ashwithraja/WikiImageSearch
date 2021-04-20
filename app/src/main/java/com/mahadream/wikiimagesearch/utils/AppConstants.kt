package com.mahadream.wikiimagesearch.utils

import androidx.annotation.Keep

object AppConstants {
    object ViewHolderType {
        var GALLERY = 2
    }

    @Keep
    interface INetworkError {
        companion object {
            const val NO_NETWORK = "500"
        }
    }
}
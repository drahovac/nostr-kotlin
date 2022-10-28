package com.bobmitchigan.com.android.viewmodel

import android.util.Patterns

interface UrlMatcher {
    fun isValidUrl(value: String): Boolean
}

class AndroidUrlMatcher : UrlMatcher {
    override fun isValidUrl(value: String): Boolean {
        return Patterns.WEB_URL.matcher(value).matches()
    }
}

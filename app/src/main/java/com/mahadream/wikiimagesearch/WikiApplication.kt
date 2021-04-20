package com.mahadream.wikiimagesearch

import android.app.Application
import android.content.Context

class WikiApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appInstance = this
    }

    init {
        appInstance = this
    }

    companion object {
        fun getContext(): Context {
            return appInstance.applicationContext
        }

        private lateinit var appInstance: WikiApplication
        fun getInstance(): WikiApplication {
            return appInstance
        }
    }
}
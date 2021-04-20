package com.mahadream.wikiimagesearch.common

import com.mahadream.wikiimagesearch.data.remote.WikiImageApiService
import com.mahadream.wikiimagesearch.data.repositories.WikiImageRepository


object RepositoryProvider {
    fun provideImageSearchRepository(apiService: WikiImageApiService): WikiImageRepository {
        return WikiImageRepository(apiService)
    }
}
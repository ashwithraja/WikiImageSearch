package com.mahadream.wikiimagesearch.data.repositories

import com.designwebtech.kambala.android.data.network.models.response.Either
import com.mahadream.wikiimagesearch.data.common.ErrorModel
import com.mahadream.wikiimagesearch.data.remote.SearchResult
import com.mahadream.wikiimagesearch.data.remote.WikiImageApiService

class WikiImageRepository(var apiService: WikiImageApiService) : BaseRepository() {
    suspend fun fetchSearchResult(query: String): Either<ErrorModel, SearchResult> {
        return processRequestWithoutData(apiService.fetchWikiImages(50, query)) {
            it!!
        }

    }
}
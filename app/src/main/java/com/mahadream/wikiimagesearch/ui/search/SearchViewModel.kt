package com.mahadream.wikiimagesearch.ui.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.designwebtech.kambala.android.data.network.models.response.Either
import com.mahadream.wikiimagesearch.data.common.ErrorModel
import com.mahadream.wikiimagesearch.data.remote.SearchResult
import com.mahadream.wikiimagesearch.data.repositories.WikiImageRepository
import com.mahadream.wikiimagesearch.ui.base.BaseAndroidViewModel
import kotlinx.coroutines.launch

class SearchViewModel(var repository: WikiImageRepository, app: Application) :
    BaseAndroidViewModel(app) {
    suspend fun getSearchResult(query: String): Either<ErrorModel, SearchResult> {
        Log.i("getSearchResult","result")
        return repository.fetchSearchResult(query)
    }
}
package com.mahadream.wikiimagesearch.common

import com.mahadream.wikiimagesearch.WikiApplication
import com.mahadream.wikiimagesearch.data.remote.WikiNetworkClient
import com.mahadream.wikiimagesearch.ui.search.SearchViewModel

object WikiViewModelProvider {
    fun provideDashBoardViewModel() : SearchViewModel{
        val repository = RepositoryProvider.provideImageSearchRepository(WikiNetworkClient().create())
        return SearchViewModel(
            repository,
            WikiApplication.getInstance()
        )
    }
}
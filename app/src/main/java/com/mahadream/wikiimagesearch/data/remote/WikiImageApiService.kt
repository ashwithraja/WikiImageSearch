package com.mahadream.wikiimagesearch.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiImageApiService {

    @GET("w/api.php")
    suspend fun fetchWikiImages(@Query("pilimit") page: Int, @Query("gpssearch") searchQuery: String?): Response<SearchResult>
    @GET("w/api.php")
     suspend fun fetchWikiImage(@Query("pilimit") page: Int, @Query("gpssearch") searchQuery: String?): Response<SearchResult>
}
package com.mahadream.wikiimagesearch.data.local

import androidx.room.*
import com.mahadream.wikiimagesearch.data.remote.SearchResult

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: SearchResult)

    @Update
    suspend fun update(profile: SearchResult)

    @Delete
    suspend fun delete(profile: SearchResult)

    @Query("delete from search_table")
    suspend fun deleteSearchResult()
}
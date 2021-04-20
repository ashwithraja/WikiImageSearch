package com.mahadream.wikiimagesearch.data.remote

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.mahadream.wikiimagesearch.data.common.BaseApiResponseModel
@Entity(tableName = "search_table")
class SearchResult  {

    val batchcomplete: String = ""

    @SerializedName("continue")
    val welcome5Continue: Continue? = null

    val query: Query? = null
}

data class Query(
    val pages: HashMap<String, Page>
)

data class Page(
    val pageid: Long,
    val ns: Long,
    val title: String,
    val index: Long,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null
)

data class Thumbnail(
    val source: String,
    val width: Long,
    val height: Long
)

data class Continue(
    val gpsoffset: Long,

    @SerializedName("continue")
    val continueContinue: String
)

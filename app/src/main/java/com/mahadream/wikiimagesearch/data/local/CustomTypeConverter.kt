package com.mahadream.wikiimagesearch.data.local

import androidx.room.Query
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mahadream.wikiimagesearch.data.remote.Page

class CustomTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToObjectQuery(data: String?): com.mahadream.wikiimagesearch.data.remote.Query? {
        if (data == null) {
            return null
        }
        return gson.fromJson(data, com.mahadream.wikiimagesearch.data.remote.Query::class.java)
    }

    @TypeConverter
    fun queryObjectToString(someObjects: com.mahadream.wikiimagesearch.data.remote.Query): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToPageObject(data: String?): com.mahadream.wikiimagesearch.data.remote.Page? {
        if (data == null) {
            return null
        }
        return gson.fromJson(data, com.mahadream.wikiimagesearch.data.remote.Page::class.java)
    }

    @TypeConverter
    fun pageObjectToString(someObjects: com.mahadream.wikiimagesearch.data.remote.Page): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToThumbnailObject(data: String?): com.mahadream.wikiimagesearch.data.remote.Page? {
        if (data == null) {
            return null
        }
        return gson.fromJson(data, com.mahadream.wikiimagesearch.data.remote.Page::class.java)
    }

    @TypeConverter
    fun thumbnailObjectToString(someObjects: com.mahadream.wikiimagesearch.data.remote.Page): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToMap(value: String): HashMap<String, String> {
        return Gson().fromJson(value, object : TypeToken<HashMap<String, Page>>() {}.type)
    }

    @TypeConverter
    fun mapToString(value: Map<String, Page>?): String {
        return if (value == null) "" else Gson().toJson(value)
    }
}

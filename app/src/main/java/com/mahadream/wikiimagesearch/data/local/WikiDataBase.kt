package com.mahadream.wikiimagesearch.data.local

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mahadream.wikiimagesearch.data.remote.SearchResult

@Database(entities = [SearchResult::class], version = 1)
@TypeConverters(CustomTypeConverter::class)

abstract class WikiDataBase : RoomDatabase() {
    abstract fun SearchDao(): SearchDao

    companion object {
        private var instance: WikiDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): WikiDataBase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, WikiDataBase::class.java,
                        "search_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}
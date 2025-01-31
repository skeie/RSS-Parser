package com.prof.rssparser.caching

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CachedFeed::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun cachedFeedDao(): CachedFeedDao

    companion object {
        private var INSTANCE: CacheDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): CacheDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CacheDatabase::class.java,
                            "rssparsercache.db"
                        ).fallbackToDestructiveMigration().build()
                    }
                    return INSTANCE as CacheDatabase
                }
            }
            return INSTANCE as CacheDatabase
        }
    }
}

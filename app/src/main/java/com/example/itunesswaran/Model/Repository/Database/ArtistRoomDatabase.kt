package com.example.itunesswaran.Model.Repository.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Artist::class], version = 1, exportSchema = false)
public abstract class ArtistRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): ArtistDao

//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    populateDatabase(database.wordDao())
//                }
//            }
//        }
//
//        suspend fun populateDatabase(wordDao: WordDao) {
//            // Delete all content here.
//            wordDao.deleteAll()
//
//            // Add sample words.
//            var word = Word("Hello")
//            wordDao.insert(word)
//            word = Word("World!")
//            wordDao.insert(word)
//
//            // TODO: Add your own words!
//        }
//    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ArtistRoomDatabase? = null

        fun getDatabase(context: Context): ArtistRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtistRoomDatabase::class.java,
                    "word_database"
                )
//                    .allowMainThreadQueries()
//                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
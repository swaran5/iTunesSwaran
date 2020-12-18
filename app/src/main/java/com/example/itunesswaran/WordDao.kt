package com.example.itunesswaran

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

//    @Query("SELECT * FROM word_table ORDER BY artistName ASC")
//    fun getAlphabetizedWords(): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE artistName LIKE '%' || :query || '%'")
     fun getFilteredArtist(query : String): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}
package com.example.itunesswaran

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")

data class Word(
    @PrimaryKey
    @ColumnInfo( name = "artistName") val artistName : String,
    @ColumnInfo( name = "imageUrl") val imageUrl : String,
    @ColumnInfo( name = "songName") val songName : String

)
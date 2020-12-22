package com.example.itunesswaran.Model.Repository.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")

data class Artist(
    @PrimaryKey
    @ColumnInfo( name = "artistName") val artistName : String,
    @ColumnInfo( name = "imageUrl") val imageUrl : String,
    @ColumnInfo( name = "songName") val songName : String

)
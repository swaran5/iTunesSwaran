package com.example.itunesswaran

data class Songs(
    val results: List<Result>
)

data class Result(
    val artistName : String,
    val trackName : String?,
    val artworkUrl100 : String,

)

data class Count(
   val count : Int,

)
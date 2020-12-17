package com.example.itunesswaran

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface Endpoints {

    @GET("search")
    fun getSongs(@Query("term") id : String): Call<Songs>

}
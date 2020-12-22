package com.example.itunesswaran.Model.Repository.Retrofit

import com.example.itunesswaran.Model.Repository.Songs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface Endpoints {

    @GET("search")
    fun getSongs(@Query("term") id : String): Call<Songs>

}
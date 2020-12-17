package com.example.itunesswaran

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var Query = ""
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var search_bar = findViewById<SearchView>(R.id.search_bar)

        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Query = query.toString()
                makerequest()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    fun makerequest() {
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getSongs(Query)

        call.enqueue(object : Callback<Songs> {
            override fun onResponse(call: Call<Songs>, response: Response<Songs>) {

                var song_recycler = findViewById<RecyclerView>(R.id.song_recycler)

                song_recycler.layoutManager = GridLayoutManager(context,2)

                val result = response.body()?.results
                val adapter = response.body()?.results?.let { MyAdapter(context, it) }
                song_recycler.adapter = adapter

            }

            override fun onFailure(call: Call<Songs>, t: Throwable) {
                Log.d("Home Screen", t.toString())
            }

        })
    }
}
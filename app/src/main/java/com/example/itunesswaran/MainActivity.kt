package com.example.itunesswaran

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val context = this
    var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = ArtistRoomDatabase.getDatabase(this)
        val repository by lazy { ArtistRepository(db.wordDao()) }

        val viewModel by lazy {
            return@lazy WordViewModel(repository)
        }

        val search_bar = findViewById<SearchView>(R.id.search_bar)
        val song_recycler = findViewById<RecyclerView>(R.id.song_recycler)

        song_recycler.layoutManager = GridLayoutManager(context,2)

        viewModel.filteredArtists.observe(this, Observer { words ->
            val adapter = MyAdapter(context, words)
            song_recycler.adapter = adapter
        })

        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                this@MainActivity.query = query.toString()
                viewModel.makerequest(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }
}
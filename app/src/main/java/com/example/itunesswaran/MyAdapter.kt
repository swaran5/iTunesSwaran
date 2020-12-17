package com.example.itunesswaran

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.Result

class MyAdapter(val context: Context, val result: List<com.example.itunesswaran.Result>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyHolder(view)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val song = result[position]
        holder.setData(song, position)
    }

    override fun getItemCount(): Int {
        return result.size
    }
    inner class MyHolder(val v: View) : RecyclerView.ViewHolder(v) {

        fun setData(obj: com.example.itunesswaran.Result, position : Int) {

            v.findViewById<TextView>(R.id.artist).text = obj.artistName
            var url = obj.artworkUrl100
            Picasso.get().load(url).into(v.findViewById<ImageView>(R.id.imageView))

        }
    }
}
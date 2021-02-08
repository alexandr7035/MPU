package com.alexandr7035.mpu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter


class SongsAdapter: Adapter<SongsAdapter.ViewHolder>() {

    private var items: List<AudioModel> = ArrayList()
    private val LOG_TAG = "DEBUG_TAG"

    fun setItems(items: List<AudioModel>) {
        Log.d(LOG_TAG, "items song")
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems(): List<AudioModel> {
        return this.items
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val songTitle: TextView = itemView.findViewById(R.id.songTitle)
        val artistName: TextView = itemView.findViewById(R.id.artistName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_song, parent, false)
        return ViewHolder(itemView = view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.songTitle.text = items[position].title
        holder.artistName.text = items[position].artist
    }

}
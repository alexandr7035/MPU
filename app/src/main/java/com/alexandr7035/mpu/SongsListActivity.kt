package com.alexandr7035.mpu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SongsListActivity : AppCompatActivity() {

    private val LOG_TAG = "DEBUG_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_list)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener { finish() }

        val adapter = SongsAdapter();

        val recycler: RecyclerView = findViewById(R.id.recycler)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        var songsList = SongsHelper.getAllSongs(this);

        Log.d(LOG_TAG, "SIZE ________" + songsList.size)


        adapter.setItems(songsList)

    }
}
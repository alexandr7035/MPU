package com.alexandr7035.mpu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "DEBUG_TAG"

    private lateinit var playBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playBtn = findViewById(R.id.playBtn)


    }


    fun allTracksBtn(v: View) {
        val intent = Intent(this, SongsListActivity::class.java)
        startActivity(intent)
    }

    fun playBtn(v: View) {
        startService(Intent(this, MusicPlayerService::class.java))
    }
}
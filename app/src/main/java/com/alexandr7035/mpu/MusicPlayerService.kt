package com.alexandr7035.mpu

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicPlayerService: Service(), MediaPlayer.OnPreparedListener {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlayerReady = false
    private val LOG_TAG = "DEBUG_TAG"

    override fun onCreate() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener(this);
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Not yet implemented")
    }

    override fun onPrepared(mp: MediaPlayer) {
        Log.d(LOG_TAG, "player is prepared")
        isPlayerReady = true;
    }


}
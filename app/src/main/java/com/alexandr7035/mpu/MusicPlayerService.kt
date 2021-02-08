package com.alexandr7035.mpu

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast




private const val ACTION_PLAY: String = "com.example.action.PLAY"

class MusicPlayerService: Service(), MediaPlayer.OnPreparedListener {

    private lateinit var player: MediaPlayer
    private var isPlayerReady = false
    private val LOG_TAG = "DEBUG_TAG"

    override fun onCreate() {

        Log.d(LOG_TAG, "service created")

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.setOnPreparedListener(this);
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        player.isLooping = true
        player.start()
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    override fun onPrepared(mp: MediaPlayer) {
        Log.d(LOG_TAG, "player is prepared")
        isPlayerReady = true;
    }


}
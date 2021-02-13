package com.alexandr7035.mpu

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast


class MusicPlayerService: Service(), MediaPlayer.OnPreparedListener {

    private lateinit var player: MediaPlayer
    private var isPlayerReady = false
    private val LOG_TAG = "DEBUG_TAG"

    private val ACTION_STRING = "ACTION"

    val ACTION_PLAY: String = "PLAY"
    val ACTION_PAUSE: String = "PAUSE"

    // Binder given to clients
    private val iBinder: IBinder = LocalBinder()

    override fun onCreate() {

        Log.d(LOG_TAG, "service created")

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.setOnPreparedListener(this);
    }

    override fun onBind(intent: Intent): IBinder {
        return iBinder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    override fun onPrepared(mp: MediaPlayer) {
        Log.d(LOG_TAG, "player is prepared")
        isPlayerReady = true;
    }


    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }


    fun getServiceStatus(): String {
        return "OK"
    }
}
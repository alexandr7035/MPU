package com.alexandr7035.mpu

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
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

    private var playerStatus: Boolean = false

    private lateinit var currentSongUri: Uri

    // Binder given to clients
    private val iBinder: IBinder = LocalBinder()

    override fun onCreate() {

        Log.d(LOG_TAG, "service created")

        currentSongUri = Settings.System.DEFAULT_RINGTONE_URI

        player = MediaPlayer.create(this, currentSongUri)
        player.setOnPreparedListener(this)
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

    fun checkIfMusicIsPlayed(): Boolean {
        return playerStatus
    }


    fun startPlaying() {
        player.start()
        playerStatus = true
    }

    fun stopPlaying() {
        player.stop()

        player.reset()
        player.setDataSource(this, currentSongUri)
        player.prepare()

        playerStatus = false
    }

    fun pausePlaying() {
        player.pause()
        playerStatus = false
    }

    fun setCurrentSong(uri: Uri) {
        currentSongUri = uri
        stopPlaying()
        startPlaying()

        Log.d(LOG_TAG, "${player.currentPosition} of ${player.duration})")
    }

    fun getCurrentTrackDuration(): Int {
        return player.duration
    }

    fun getCurrentTrackPosition(): Int {
        return player.duration
    }
}
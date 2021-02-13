package com.alexandr7035.mpu

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "DEBUG_TAG"

    private lateinit var playBtn: ImageView

    private val ACTION_STRING = "ACTION"

    private lateinit var musicService: MusicPlayerService

    var serviceBound = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playBtn = findViewById(R.id.playBtn)

        val intent = Intent(this, MusicPlayerService::class.java)
        intent.putExtra(ACTION_STRING, "PLAY")
        startService(Intent(this, MusicPlayerService::class.java))
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }


    fun allTracksBtn(v: View) {
        val intent = Intent(this, SongsListActivity::class.java)
        startActivity(intent)
    }


    fun playBtn(v: View) {
        Log.d(LOG_TAG, musicService.getServiceStatus())
    }


    //Binding this Client to the AudioPlayer Service
    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance

            musicService = (service as MusicPlayerService.LocalBinder).getService()
            Toast.makeText(this@MainActivity, "Service bind", Toast.LENGTH_LONG).show()
            serviceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            serviceBound = false
        }
    }


    fun doUnbindService() {
        if (serviceBound) {
            // Detach our existing connection.
            unbindService(serviceConnection)
            serviceBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnbindService()
    }

}
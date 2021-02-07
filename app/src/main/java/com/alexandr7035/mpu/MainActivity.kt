package com.alexandr7035.mpu

import android.content.ContentUris
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "DEBUG_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun allTracksBtn(v: View) {
        val intent = Intent(this, SongsListActivity::class.java)
        startActivity(intent)
    }
}
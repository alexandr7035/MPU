package com.alexandr7035.mpu

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class SongsHelper {

    companion object {

        val LOG_TAG = "DEBUG_TAG"

        fun getAllSongs(context: Context): ArrayList<AudioModel> {

            val audioList: ArrayList<AudioModel> = ArrayList()

            val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

            val info = arrayOf(
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.ALBUM,
                    MediaStore.Audio.Media.DURATION
            )


            val cursor: Cursor? = context.contentResolver.query(uri, info,
                    null, null, null)

            if (cursor != null) {

                while (cursor.moveToNext()) {

                    val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))

                    val track = AudioModel(id, title, artist, duration)

                    audioList.add(track)
                }

                cursor.close()

            }

            Log.d(LOG_TAG, "TOTAL SONGS COUNT ${audioList.size}")
            return audioList
        }


    }

}
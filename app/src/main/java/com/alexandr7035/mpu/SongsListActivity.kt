package com.alexandr7035.mpu

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlin.collections.ArrayList

class SongsListActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private val LOG_TAG = "DEBUG_TAG"
    private lateinit var searchVisibleLiveData: MutableLiveData<Boolean>
    private var searchIsVisible: Boolean = false
    private lateinit var searchView: LinearLayout
    private lateinit var searchEditText: EditText

    private lateinit var toolbarNavigationIcon: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_list)

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_songs_list)
        toolbar.setOnMenuItemClickListener(this)
        // Show back arrow
        toolbar.setNavigationOnClickListener { finish() }
        // Save navigation icon in order to use it again
        toolbarNavigationIcon = toolbar.navigationIcon!!

        // Recycler view
        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = SongsAdapter();
        recycler.adapter = adapter
        var songsList: ArrayList<AudioModel> = SongsHelper.getAllSongs(this);
        adapter.setItems(songsList)

        // Search
        searchVisibleLiveData = MutableLiveData()
        searchVisibleLiveData.value = false

        searchView = findViewById(R.id.searchView)
        searchEditText = searchView.findViewById(R.id.searchEditText)

        searchVisibleLiveData.observe(this, Observer {

            if (it) {
                Log.d(LOG_TAG, "show search")
                searchView.visibility = View.VISIBLE
                toolbar.navigationIcon = null
                toolbar.menu.findItem(R.id.item_search).isVisible = false
            }
            else {
                Log.d(LOG_TAG, "hide search")
                searchView.visibility = View.GONE
                toolbar.navigationIcon = toolbarNavigationIcon
                toolbar.menu.findItem(R.id.item_search).isVisible = true

                searchEditText.setText("")
            }
        })


        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val filteredList: ArrayList<AudioModel> = ArrayList()

                if (charSequence.toString() != "") {
                    for (song in songsList) {
                        if (song.title.toLowerCase().contains(charSequence.toString().toLowerCase().trim { it <= ' ' })) {
                            filteredList.add(song)
                        }
                    }
                    adapter.setItems(filteredList)
                } else {
                    adapter.setItems(songsList)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

    }

    override fun onMenuItemClick(item: MenuItem): Boolean {

        if (item.itemId == R.id.item_search) {
            searchVisibleLiveData.postValue(true)
        }

        return super.onOptionsItemSelected(item)
    }


    fun hideSearchBtn(v: View) {
        searchVisibleLiveData.value = false
    }
}
package com.dicodingsubmission.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingsubmission.R
import com.dicodingsubmission.adapter.ItemListHomeAdapter
import com.dicodingsubmission.databinding.ActivityMainBinding
import com.dicodingsubmission.model.GamesDescData
import com.dicodingsubmission.model.GamesDescDataResult

class MainActivity : AppCompatActivity() {

    // VIEW BINDING
    private lateinit var binding: ActivityMainBinding

    // ADAPTER
    private lateinit var gameListAdapter: ItemListHomeAdapter
    private var gameList: ArrayList<GamesDescData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setListClickAction()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onClick(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun onClick(onMenuClick: Int) { // TO HANDLE THE ITEM CLICK LISTENER
        when (onMenuClick) {
            R.id.mAbout -> { // TO GO TO ABOUTACTIVITY
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
    }

    private fun setupRecycler() { // SETUP RECYCLERVIEW
        binding.rcHomeList.setHasFixedSize(true)
        gameList.addAll(GamesDescDataResult.gameListData)
        binding.rcHomeList.layoutManager = LinearLayoutManager(this@MainActivity)
        gameListAdapter = ItemListHomeAdapter(gameList)
        binding.rcHomeList.adapter = gameListAdapter
    }

    private fun setListClickAction() {
        gameListAdapter.setOnItemClickCallback(
            object : ItemListHomeAdapter.OnItemClickCallback {
                override fun onItemClick(desc: GamesDescData) {
                    val mainToDetailIntent = // INITIALIZE THE DATA TO SEND TO DETAILACTIVITY WITH PUTEXTRA
                        Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra(DetailActivity.GET_GAME_NAME, desc.name)
                            putExtra(DetailActivity.GET_GAME_DESC, desc.desc)
                            putExtra(DetailActivity.GET_GAME_RD, desc.release_date)
                            putExtra(DetailActivity.GET_GAME_RATING, desc.rating)
                            putExtra(DetailActivity.GET_GAME_IMAGE_SMALL, desc.image)
                            putExtra(DetailActivity.GET_GAME_IMAGE_LARGE, desc.image_large)
                        }
                    startActivity(mainToDetailIntent)
                }
            })


    }
}
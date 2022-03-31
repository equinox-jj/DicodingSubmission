package com.dicodingsubmission.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.dicodingsubmission.R
import com.dicodingsubmission.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    // VIEW BINDING
    private lateinit var binding: ActivityDetailBinding

    companion object { // FETCH / GET ALL DATA FROM PUTEXTRA IN MAINACTIVITY
        const val GET_GAME_NAME = "get_game_name"
        const val GET_GAME_DESC = "get_game_desc"
        const val GET_GAME_RD = "get_game_rd"
        const val GET_GAME_RATING = "get_game_rating"
        const val GET_GAME_IMAGE_SMALL = "get_game_image_small"
        const val GET_GAME_IMAGE_LARGE = "get_game_image_large"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOOLBAR
        toolbarSetup()

        fetchDataFromMain()

    }

    private fun fetchDataFromMain() { // TO SHOWING THE DATA IN DETAILACTIVITY
        binding.tvDetailTitle.setText(intent.getStringExtra(GET_GAME_NAME))
        binding.tvDetailDesctData.setText(intent.getStringExtra(GET_GAME_DESC))
        binding.tvDetailRating.setText(intent.getDoubleExtra(GET_GAME_RATING, 0.0).toString())
        binding.tvDetailRelease.setText(intent.getStringExtra(GET_GAME_RD))
        binding.ivSmallDetail.load(intent.getIntExtra(GET_GAME_IMAGE_SMALL, 0)) {
            transformations(CircleCropTransformation())
        }
        binding.ivLargeDetail.load(intent.getIntExtra(GET_GAME_IMAGE_LARGE, 0))
    }

    private fun toolbarSetup() { // ONBACKPRESSED IN TOOLBAR
        setSupportActionBar(binding.tDetailAct)
        binding.tDetailAct.setTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish() // DESTROY THE DETAILACTIVITY
        return super.onOptionsItemSelected(item)
    }

}
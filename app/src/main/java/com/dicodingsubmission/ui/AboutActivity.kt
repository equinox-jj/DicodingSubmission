package com.dicodingsubmission.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.dicodingsubmission.R
import com.dicodingsubmission.databinding.ActivityAboutBinding
import com.dicodingsubmission.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {

    // VIEW BINDING
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOOLBAR
        toolbarSetup()

    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.tAboutAct)
        binding.tAboutAct.setTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}
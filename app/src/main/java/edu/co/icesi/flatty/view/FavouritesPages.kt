package edu.co.icesi.flatty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityFavouritesPagesBinding
import edu.co.icesi.flatty.databinding.ActivityProfileSearchedPageBinding

class FavouritesPages : AppCompatActivity() {

    private lateinit var binding: ActivityFavouritesPagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouritesPagesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnBackToSearchedPage.setOnClickListener {
           finish()
        }

    }
}
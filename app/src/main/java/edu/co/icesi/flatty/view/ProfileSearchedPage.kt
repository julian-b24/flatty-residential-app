package edu.co.icesi.flatty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityEditProfileBinding
import edu.co.icesi.flatty.databinding.ActivityProfileSearchedPageBinding

class ProfileSearchedPage : AppCompatActivity() {


    private lateinit var binding: ActivityProfileSearchedPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSearchedPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnBackToSearchResident.setOnClickListener {
            finish()
        }
        binding.goToFavoritos.setOnClickListener{
            var intent = Intent(this, FavouritesPages::class.java)
            startActivity(intent)
        }


    }
}
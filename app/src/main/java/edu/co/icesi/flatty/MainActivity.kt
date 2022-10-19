package edu.co.icesi.flatty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.flatty.databinding.ActivityFavouritesPagesBinding
import edu.co.icesi.flatty.databinding.ActivityMainBinding
import edu.co.icesi.flatty.view.SearchResident

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startActivity(Intent(this@MainActivity, SearchResident::class.java))
    }
}
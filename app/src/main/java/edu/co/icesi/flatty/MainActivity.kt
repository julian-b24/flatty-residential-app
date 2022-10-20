package edu.co.icesi.flatty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.flatty.databinding.WelcomePageBinding
import edu.co.icesi.flatty.view.ResidentProfileActivity

class MainActivity : AppCompatActivity() {

    private val binding: WelcomePageBinding by lazy{
        WelcomePageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startActivity(Intent(this@MainActivity, ResidentProfileActivity::class.java))
    }
}
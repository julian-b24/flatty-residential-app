package edu.co.icesi.flatty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.flatty.databinding.WelcomePageBinding
import edu.co.icesi.flatty.view.LoginPageResident

class MainActivity : AppCompatActivity() {

    private lateinit var binding: WelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this,LoginPageResident::class.java)
            startActivity(intent)
        }
    }
}
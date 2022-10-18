package edu.co.icesi.flatty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.flatty.databinding.LoginPageResidentBinding

class ResidentProfilePage : AppCompatActivity() {

    private lateinit var binding: LoginPageResidentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
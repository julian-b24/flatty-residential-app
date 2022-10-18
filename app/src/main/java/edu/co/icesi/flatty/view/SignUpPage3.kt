package edu.co.icesi.flatty.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.co.icesi.flatty.databinding.SignUpPage3Binding

class SignUpPage3 : AppCompatActivity() {
    private lateinit var binding: SignUpPage3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPage3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.returnP3Constraint.setOnClickListener{
            finish()
        }
    }
}
package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.co.icesi.flatty.databinding.SignUpPage1Binding

class SignUpPage1 : AppCompatActivity() {

    private lateinit var binding: SignUpPage1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPage1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.returnP1Constrain.setOnClickListener{
            finish()
        }

        binding.continuePage1Btn.setOnClickListener{
            var intent = Intent(this, SignUpPage2::class.java)
            restarTextFields()
            startActivity(intent)
        }
    }

    private fun restarTextFields(){
        binding.nameTI.setText("")
        binding.phoneTI.setText("")
        binding.numberApartmentTI.setText("")
        binding.ageTI.setText("")
    }
}
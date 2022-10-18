package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.co.icesi.flatty.databinding.SignUpPage2Binding

class SignUpPage2 : AppCompatActivity() {

    private lateinit var binding: SignUpPage2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPage2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.returnP2Constraint.setOnClickListener{
            finish()
        }

        binding.continuePage2Btn.setOnClickListener {
            var intent = Intent(this, SignUpPage3::class.java)
            restarTextFields()
            startActivity(intent)
        }
    }

    private fun restarTextFields(){
        binding.emailP2TI.setText("")
        binding.passwordP2TI.setText("")
        binding.confirmPasswordP2TI.setText("")
    }
}
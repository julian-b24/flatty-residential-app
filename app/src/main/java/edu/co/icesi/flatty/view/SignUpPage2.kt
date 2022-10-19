package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.co.icesi.flatty.databinding.SignUpPage2Binding

class SignUpPage2 : AppCompatActivity() {

    private lateinit var binding: SignUpPage2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPage2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val name = intent.extras?.getString("name")
        val phone = intent.extras?.getString("phone")
        val numberApartment = intent.extras?.getString("numberApartment")
        val age = intent.extras?.getString("age")

        binding.returnP2Constraint.setOnClickListener{
            finish()
        }

        binding.continuePage2Btn.setOnClickListener {
            var intent = Intent(this, SignUpPage3::class.java).apply {
                putExtra("name", name)
                putExtra("phone", phone)
                putExtra("numberApartment", numberApartment)
                putExtra("age", age)
                putExtra("email", binding.emailET.editText?.text.toString())
                putExtra("password", binding.passwordET.editText?.text.toString())
            }
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
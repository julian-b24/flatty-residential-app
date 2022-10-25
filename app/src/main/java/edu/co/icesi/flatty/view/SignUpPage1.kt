package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
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
            var intent = Intent(this, SignUpPage2::class.java).apply {
                putExtra("name", binding.nameP1ET.editText?.text.toString())
                putExtra("phone", binding.phoneP1ET.editText?.text.toString())
                putExtra("numberApartment", binding.numberApartmentP1ET.editText?.text.toString())
                putExtra("age", binding.ageP1ET.editText?.text.toString())
            }
            restarTextFields()
            startActivity(intent)
        }

        binding.nameTI.doOnTextChanged{text, start, before, count ->  
            if(!text!!.matches("[a-zA-Z\\sñÑ]*".toRegex())){
                binding.nameTI.error = "El nombre no puede contener caractes especiales, ni numeros"
            }else{
                binding.nameTI.error = null
            }
        }

        binding.numberApartmentTI.doOnTextChanged{text, start, before, count ->
            if(!text!!.matches("[a-zA-Z][0-9]*".toRegex())){
                binding.numberApartmentTI.error = "El apartamento debe tener la letra de la torre y seguidamente el numero"
            }else{
                binding.numberApartmentTI.error = null
            }
        }
    }

    private fun restarTextFields(){
        binding.nameTI.setText("")
        binding.phoneTI.setText("")
        binding.numberApartmentTI.setText("")
        binding.ageTI.setText("")
    }
}
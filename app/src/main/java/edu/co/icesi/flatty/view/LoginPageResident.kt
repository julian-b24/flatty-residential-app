package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.LoginPageResidentBinding

class LoginPageResident : AppCompatActivity() {
    private lateinit var binding: LoginPageResidentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageResidentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var selectedResident = true

        binding.iconResidentBtn.setOnClickListener {
            changeTypeUser(binding.iconResidentBtn, binding.iconGuardBtn)
            binding.registerConstraint.visibility = View.VISIBLE
            restarTextFields()
            selectedResident = true
        }

        binding.iconGuardBtn.setOnClickListener {
            changeTypeUser(binding.iconGuardBtn, binding.iconResidentBtn)
            binding.registerConstraint.visibility = View.INVISIBLE
            restarTextFields()
            selectedResident = false
        }

        binding.registerTV.setOnClickListener {
            val intent = Intent(this, SignUpPage1::class.java)
            startActivity(intent)
        }


        binding.loginBtn.setOnClickListener {
            if(!selectedResident){
                Log.e(">>>", "Guardia")
                var intent = Intent(this, SearchResident::class.java)
                startActivity(intent)

            }else {
                Log.e(">>>", "Residente")
                var intent = Intent(this, ResidentProfilePage::class.java)
                startActivity(intent)
            }
        }



    }

    fun changeTypeUser(selected: MaterialButton, current: MaterialButton) {
        selected.setIconTintResource(R.color.orange)
        selected.setBackgroundColor(
            ContextCompat.getColor(binding.root.context, R.color.orange_degraded)
        )
        current.setIconTintResource(R.color.gray)
        current.setBackgroundColor(
            ContextCompat.getColor(binding.root.context, R.color.gray_degraged)
        )
    }

    private fun restarTextFields() {
        binding.emailTI.setText("")
        binding.passwordTI.setText("")
    }
}
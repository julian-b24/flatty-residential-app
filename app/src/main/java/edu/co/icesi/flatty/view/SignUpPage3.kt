package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.co.icesi.flatty.databinding.SignUpPage3Binding
import edu.co.icesi.flatty.viewModel.SignUpPageViewModel
import edu.co.icesi.flatty.viewModel.AuthResult
import java.util.*

class SignUpPage3 : AppCompatActivity(){
    private lateinit var binding: SignUpPage3Binding
    private val viewmodel: SignUpPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPage3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewmodel.authState.observe(this){
            when(it.result){
                AuthResult.IDLE-> {
                    //Log.e(">>>", "Entro este hpta")
                    Toast.makeText(this, "Registrado con éxito", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@SignUpPage3, LoginPageResident::class.java))

                }
                AuthResult.SUCCESS -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG)

                }
                AuthResult.FAIL -> {
                    Toast.makeText(this, "Registrado con éxito", Toast.LENGTH_LONG)

                }
            }
        }

        val name = intent.extras?.getString("name")
        val phone = intent.extras?.getString("phone")
        val numberApartment = intent.extras?.getString("numberApartment")
        val age = intent.extras?.getString("age")
        val email = intent.extras?.getString("email")
        val password = intent.extras?.getString("password")

        var code = generatedRandomCode()
        Toast.makeText(this, "Codigo = $code", Toast.LENGTH_SHORT).show()

        binding.returnP3Constraint.setOnClickListener{
            finish()
        }

        binding.resendP3TV.setOnClickListener{
            code = generatedRandomCode()
            Toast.makeText(this, code.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.validatePage3Btn.setOnClickListener {
            var sentCode = getCodeSent()
            if(sentCode == code){
                viewmodel.createAccount(name!!, phone!!, numberApartment!!, age!!, email!!, password!!)
                Toast.makeText(this, "Cuenta creada correctamente", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Codigo incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generatedRandomCode() : String {
        var newCode = ""
        var start = 0
        var end = 9
        for (i in 1..4) {
            newCode += Random(System.nanoTime()).nextInt(end - start + 1) + start
        }
        return newCode
    }

    private fun getCodeSent(): String {
        var receivedCode = ""
        receivedCode += binding.verifyCode1TI.text.toString()
        receivedCode += binding.verifyCode2TI.text.toString()
        receivedCode += binding.verifyCode3TI.text.toString()
        receivedCode += binding.verifyCode4TI.text.toString()

        return receivedCode
    }
}
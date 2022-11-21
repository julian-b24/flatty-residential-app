package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.LoginPageResidentBinding
import edu.co.icesi.flatty.model.Guard
import edu.co.icesi.flatty.model.Resident
import edu.co.icesi.flatty.viewModel.LoginPageViewModel
import edu.co.icesi.flatty.viewModel.UserType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginPageResident : AppCompatActivity() {
    private lateinit var binding: LoginPageResidentBinding
    private val viewModel: LoginPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageResidentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.loggedUserType.observe(this) {
            when (it.userType) {
                UserType.RESIDENT -> {
                    Log.e(">>>", "IN A")
                    startActivity(Intent(this, ResidentProfilePage::class.java))
                    //finish()
                }
                UserType.GUARD -> {
                    Log.e(">>>", "IN B")
                    startActivity(Intent(this, SearchResident::class.java))
                    //finish()
                }
                UserType.NONE -> {
                    Log.e(">>>", "IN C")
                }
            }
        }

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
            val email = binding.emailTI.text.toString()
            val password = binding.passwordTI.text.toString()
            viewModel.loginUser(email.trim(), password, selectedResident)
            /*
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            if(!selectedResident) {
                val fbguard = Firebase.auth.currentUser
                Firebase.firestore.collection("guards").document(fbguard.uid).get().addOnSuccessListener {
                    val guard = it.toObject(Guard::class.java)

                    //2.Salvar el usuario
                    saveGuard(guard!!)
                    startActivity(Intent(this, SearchResident::class.java))
                    finish()
                }
            }


            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                if (!selectedResident) {
                    val fbguard = Firebase.auth.currentUser
                    if (fbguard!!.isEmailVerified) {
                        //Dar acceso
                        //1.Obtener usuario almacenado en firestore
                        Firebase.firestore.collection("guards").document(fbguard.uid).get()
                            .addOnSuccessListener {
                                val guard = it.toObject(Guard::class.java)

                                //2.Salvar el usuario
                                saveGuard(guard!!)
                                startActivity(Intent(this, SearchResident::class.java))
                                finish()
                            }
                    } else {
                        Toast.makeText(this, "Su email no esta verificado", Toast.LENGTH_LONG)
                    }
                } else {
                    val fbresident = Firebase.auth.currentUser
                    val resident =
                        Firebase.firestore.collection("residents").document(fbresident!!.uid).get()
                    //2.Salvar el usuario
                    saveResident(resident!!)
                    startActivity(Intent(this, ResidentProfilePage::class.java))
                    finish()

                }
            }
             */
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

    fun restarTextFields() {
        binding.emailTI.setText("")
        binding.passwordTI.setText("")
    }

    fun saveResident(resident: Resident) {
        val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
        val json = Gson().toJson(resident)
        sp.edit().putString("resident", json).apply()
    }

    fun saveGuard(guard: Guard) {
        val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
        val json = Gson().toJson(guard)
        sp.edit().putString("guard", json).apply()
    }
}

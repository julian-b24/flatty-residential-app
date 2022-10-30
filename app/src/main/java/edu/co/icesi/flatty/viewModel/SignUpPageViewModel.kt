package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.Resident
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUpPageViewModel : ViewModel(){

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting....")
    )
    val authState: LiveData<AuthState> get() = _authState

    fun createAccount(name:String, phone:String, numberApartment:String, age:String, email:String, password:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val result = Firebase.auth.createUserWithEmailAndPassword(email, password).await()

                val resident = Resident(Firebase.auth.currentUser!!.uid ,name,  phone, numberApartment, age, email, password)
                Firebase.firestore.collection("Residents")
                    .document(resident.id).set(resident)

                withContext(Dispatchers.Main){
                    _authState.value = AuthState(AuthResult.SUCCESS, "Success")
                }

            }catch (ex:Exception){
                Log.e(">>>",ex.localizedMessage)
                _authState.value = AuthState(
                    AuthResult.FAIL, ex.localizedMessage
                )
            }
        }


    }
}

data class AuthState(val result: AuthResult, val message: String)

enum class AuthResult{ IDLE, FAIL, SUCCESS, }
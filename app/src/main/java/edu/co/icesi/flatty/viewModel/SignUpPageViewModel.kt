package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.Chat
import edu.co.icesi.flatty.model.Message
import edu.co.icesi.flatty.model.Resident
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

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
                Firebase.firestore.collection("residents").document(resident.id).set(resident).addOnCompleteListener {
                    if(it.isSuccessful){
                        it.result;
                        Log.e(">>>","Exito")
                        createChat(resident.id)
                    }else{
                        val alfa = it.exception
                        Log.e(">>>", alfa!!.localizedMessage)

                    }
                }

                withContext(Dispatchers.Main){
                    _authState.value = AuthState(AuthResult.SUCCESS, "Success")
                }

            }catch (ex:Exception){
                Log.e(">>>",ex.localizedMessage)
                withContext(Dispatchers.Main) {
                    _authState.value = AuthState(
                        AuthResult.FAIL, ex.localizedMessage
                    )
                }
            }
        }
    }

    fun sendVerificationEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener{
            //Toast.makeText(this,"Verifique su email antes de entrar", Toast.LENGTH_LONG).show()
        }?.addOnFailureListener{
            //Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
        }
    }

    fun createChat(residentId:String) {
        val guardId = "qgq1ns9Ycmg9KaJKv44v2frHceG2"
        val defMessage = "Aqui porteria"
        var uuidLastMessage = UUID.randomUUID().toString()

        var newChat = Chat(residentId,uuidLastMessage)
        var newMessage = Message(uuidLastMessage,guardId,0,defMessage)

        Firebase.firestore.collection("chats").document(residentId).set(newChat)

        Firebase.firestore.collection("chats").document(residentId)
            .collection("room").document(guardId)
            .collection("messages").document(uuidLastMessage).set(newMessage)
    }
}

data class AuthState(val result: AuthResult, val message: String)

enum class AuthResult{ IDLE, FAIL, SUCCESS, }
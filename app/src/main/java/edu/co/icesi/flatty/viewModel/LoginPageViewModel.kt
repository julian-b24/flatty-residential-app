package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginPageViewModel: ViewModel() {

    private val _loggedUserType = MutableLiveData(
        LoggedUser(UserType.NONE)
    )
    val loggedUserType: LiveData<LoggedUser> get() = _loggedUserType

    fun loginUser(email: String, password: String, selectedResident: Boolean): UserType{

        var loggedUserType: UserType = UserType.NONE

        viewModelScope.launch(Dispatchers.IO) {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            if(!selectedResident) {
                val fbguard = Firebase.auth.currentUser
                Firebase.firestore.collection("guards").document(fbguard!!.uid).get().await()//addOnSuccessListener {
                    //val guard = it.toObject(Guard::class.java)

                    //2.Salvar el usuario
                    //saveGuard(guard!!)
                    //startActivity(Intent(this, SearchResident::class.java))
                    //finish()
                //}
                withContext(Dispatchers.Main){
                    _loggedUserType.value = LoggedUser(UserType.GUARD)
                }

            }else{
                val fbresident = Firebase.auth.currentUser
                //val resident =
                Log.e(">>>","Antes")
                Firebase.firestore.collection("residents").document(fbresident!!.uid).get().await()
                Log.e(">>>","Después")
                //2.Salvar el usuario
                //saveResident(resident!!)
                //startActivity(Intent(this, ResidentProfilePage::class.java))
                //finish()
                withContext(Dispatchers.Main){
                    _loggedUserType.value = LoggedUser(UserType.RESIDENT)
                }
            }
        }

        return loggedUserType
    }

    /*
    fun saveResident(resident: Resident){
        val sp = getSharedPreferences("appmoviles", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(resident)
        sp.edit().putString("resident", json).apply()
    }

    fun saveGuard(guard: Guard){
        val sp = getSharedPreferences("appmoviles", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(guard)
        sp.edit().putString("guard", json).apply()
    }
    */
}

data class LoggedUser(val userType: UserType)

enum class UserType {
    RESIDENT, GUARD, NONE
}
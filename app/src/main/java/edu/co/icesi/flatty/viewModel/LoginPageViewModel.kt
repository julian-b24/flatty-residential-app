package edu.co.icesi.flatty.viewModel

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

    fun loginUser(email: String, password: String, selectedResident: Boolean){

        viewModelScope.launch(Dispatchers.IO) {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            if(!selectedResident) {
                val fbguard = Firebase.auth.currentUser
                Firebase.firestore.collection("guards").document(fbguard!!.uid).get().await()
                withContext(Dispatchers.Main){
                    _loggedUserType.value = LoggedUser(UserType.GUARD)
                }

            }else{
                val fbresident = Firebase.auth.currentUser
                Firebase.firestore.collection("residents").document(fbresident!!.uid).get().await()
                withContext(Dispatchers.Main){
                    _loggedUserType.value = LoggedUser(UserType.RESIDENT)
                }
            }
        }
    }

}

data class LoggedUser(val userType: UserType)

enum class UserType {
    RESIDENT, GUARD, NONE
}
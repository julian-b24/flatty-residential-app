package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatResidentViewModel:ViewModel(){
    private var userId = Firebase.auth.currentUser?.uid
    private val guardId = "ZVzV35a1W6htTBxh1DIm"

    private val arrayMessages = arrayListOf<Message>()
    private val _messages: MutableLiveData<ArrayList<Message>> = MutableLiveData(arrayListOf())
    val messages: LiveData<ArrayList<Message>> get() = _messages

    fun subcribeToMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore.collection("chats")
                .document(userId!!).collection("rooms")
                .document(guardId).collection("messages")
                .addSnapshotListener { data, e ->
                    for (doc in data!!.documentChanges) {
                        if (doc.type.name == "ADDED") {
                            val msg = doc.document.toObject(Message::class.java)
                            Log.e(">>>", msg.text)
                            arrayMessages.add(msg)
                            _messages.value = arrayMessages
                        }
                    }
                }
            /*.whereEqualTo("friendId", guardId).get().await()
        for (doc in result.documents) {
            val chat = doc.toObject(Chat::class.java)
            withContext(Dispatchers.Main){subcribeRealTimeMessage(chat!!)}
        }*/
        }
    }
}
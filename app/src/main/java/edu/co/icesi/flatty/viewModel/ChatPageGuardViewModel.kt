package edu.co.icesi.flatty.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ChatPageGuardViewModel: ViewModel() {

    private var guardId = Firebase.auth.currentUser?.uid

    private val arrayMessages = arrayListOf<Message>()
    private val _messages: MutableLiveData<ChatState> = MutableLiveData(ChatState())
    val messages: LiveData<ChatState> get() = _messages

    fun subcribeToMessage(residentId:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val listener= Firebase.firestore.collection("chats")
                .document(residentId!!)
                .collection("room")
                .document(guardId!!)
                .collection("messages")
                .orderBy("date")
                .limitToLast(10)

                .addSnapshotListener { data, e ->
                    for (doc in data!!.documentChanges) {
                        if (doc.type.name == "ADDED") {
                            val msg = doc.document.toObject(Message::class.java)
                            arrayMessages.add(msg)
                            _messages.value = ChatState(arrayMessages, State.WAITING)
                        }
                    }
                }
        }
    }

    fun saveMsg (msg:Message, residentId:String){
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("chats")
                .document(residentId!!).collection("room")
                .document(guardId!!).collection("messages")
                .document(UUID.randomUUID().toString())
                .set(msg)
        }
    }
}
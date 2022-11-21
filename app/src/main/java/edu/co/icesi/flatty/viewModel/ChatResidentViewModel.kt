package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class ChatResidentViewModel : ViewModel() {
    private var userId = Firebase.auth.currentUser?.uid
    private val guardId = "qgq1ns9Ycmg9KaJKv44v2frHceG2"//"ZVzV35a1W6htTBxh1DIm"

    private val arrayMessages = arrayListOf<Message>()
    private val _messages: MutableLiveData<ChatState> = MutableLiveData(ChatState())
    val messages: LiveData<ChatState> get() = _messages

    fun subcribeToMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            //Log.e(">>>",userId!!)
            //Log.e(">>>",guardId)

            val listener=Firebase.firestore.collection("chats")
                .document(userId!!)
                .collection("room")
                .document(guardId)
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

    /*fun getMessages(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore.collection("chats")
                .document(userId!!).collection("room")
                .document(guardId).collection("messages").get().await()

            arrayMessages.addAll(result.map { msg -> msg.toObject(Message::class.java) })
            withContext(Dispatchers.Main){_messages.value = ChatState(arrayMessages, State.LOADING)}
        }
    }*/

    fun saveMsg (msg:Message){
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("chats")
                .document(userId!!).collection("room")
                .document(guardId).collection("messages")
                .document(msg.id)
                .set(msg)

            Firebase.firestore.collection("chats")
                .document(userId!!).update("lastMessage",msg.id.toString())
        }
    }
}

data class ChatState(
    var messages:ArrayList<Message> = arrayListOf(),
    var state:State = State.LOADING
)

enum class State{
    WAITING,
    LOADING
}
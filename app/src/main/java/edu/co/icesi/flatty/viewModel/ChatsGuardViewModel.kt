package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.Chat
import edu.co.icesi.flatty.model.ChatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class ChatsGuardViewModel : ViewModel() {

    private val userId = Firebase.auth.currentUser?.uid

    private val arrayChats = arrayListOf<ChatItem>()
    private val _chats: MutableLiveData<ArrayList<ChatItem>> = MutableLiveData(arrayListOf<ChatItem>())
    val chats: LiveData<ArrayList<ChatItem>> get() = _chats

    fun suscribeToChats() {
        viewModelScope.launch(Dispatchers.IO) {
            var friendId: String = ""
            var idMessage: String = ""
            var message: String = ""
            var dateLong: Long = 0
            var name: String = ""

            Firebase.firestore.collection("chats")
                .addSnapshotListener { data, e ->
                    for (doc in data!!.documentChanges) {
                        if (doc.type.name == "ADDED") {
                            friendId = doc.document.get("id") as String
                            idMessage = doc.document.get("lastMessage") as String

                            viewModelScope.launch(Dispatchers.IO){
                                var msg = Firebase.firestore.collection("chats").document(friendId)
                                    .collection("room").document(userId!!)
                                    .collection("messages").document(idMessage).get().await()

                                message = msg.get("text") as String
                                dateLong = msg.get("date") as Long

                                val user = Firebase.firestore.collection("residents")
                                    .document(friendId).get().await()

                                name = user.get("name") as String

                                var chat = ChatItem(friendId,idMessage,name, message, dateLong)
                                arrayChats.add(chat)
                                withContext(Dispatchers.Main){_chats.value = arrayChats}
                            }
                        }
                    }
                }



            /*var msg = Firebase.firestore.collection("chats").document(friendId)
                .collection("room").document(userId!!)
                .collection("messages").document(idMessage).get()

            Log.e(">>>", msg.toString())

            message = msg.get("text") as String
            dateLong = msg.get("date") as String

            Log.e(">>>", message)
            Log.e(">>>", dateLong)

            val user = Firebase.firestore.collection("residents")
                .document(friendId).get().await()

            name = user.get("name") as String
            Log.e(">>>", name)*/

        }
    }

    /*
    fun getChat(doc: DocumentChange) {

        var friendId: String = doc.document.get("id") as String
        var idMessage: String = doc.document.get("lastMessage") as String
        Log.e(">>>", friendId)
        Log.e(">>>", idMessage)

        viewModelScope.launch(Dispatchers.IO) {
            val msg = Firebase.firestore.collection("chats").document(friendId)
                .collection("room").document(userId!!)
                .collection("messages").document(idMessage).get().await()

            var message = msg.get("text") as String
            var dateLong = msg.get("date") as Long

            Log.e(">>>", message)
            Log.e(">>>", dateLong.toString())

            val user = Firebase.firestore.collection("residents")
                .document(friendId).get().await()

            var name = user.get("name")
            Log.e(">>>", name.toString())

            //ChatItem(friendId,idMessage,name, message, dateLong)
        }
    }*/

}
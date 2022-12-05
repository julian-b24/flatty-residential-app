package edu.co.icesi.flatty.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.model.ChatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ChatsGuardViewModel : ViewModel() {

    private val userId = Firebase.auth.currentUser?.uid

    private val arrayChats = arrayListOf<ChatItem>()
    private val _chats: MutableLiveData<ArrayList<ChatItem>> =
        MutableLiveData(arrayListOf<ChatItem>())
    val chats: LiveData<ArrayList<ChatItem>> get() = _chats

    fun suscribeToChats() {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("chats")
                .addSnapshotListener { data, e ->

                    var temporalChats: ArrayList<ChatItem> = arrayListOf()

                    for (doc in data!!.documentChanges) {
                        if (doc.type.name == "ADDED") {

                            val friendId = doc.document.get("id") as String
                            val idMessage = doc.document.get("lastMessage") as String

                            var temporalChatItem: ChatItem = ChatItem(friendId, idMessage)
                            temporalChats.add(temporalChatItem)

                            /*
                            viewModelScope.launch(Dispatchers.IO) {
                                var friendId: String = ""
                                var idMessage: String = ""
                                var message: String = ""
                                var dateLong: Long = 0
                                var name: String = ""

                                friendId = doc.document.get("id") as String
                                idMessage = doc.document.get("lastMessage") as String
                                Log.e(">>>", "Chat Friend Id: ${friendId}, Chat Id message: ${idMessage}")

                                var msg = Firebase.firestore.collection("chats").document(friendId)
                                    .collection("room").document(userId!!)
                                    .collection("messages").document(idMessage).get().await()

                                message = msg.get("text") as String
                                dateLong = msg.get("date") as Long

                                val user = Firebase.firestore.collection("residents")
                                    .document(friendId).get().await()

                                name = user.get("name") as String

                                var chat = ChatItem(friendId, idMessage, name, message, dateLong)
                                arrayChats.add(chat)
                                withContext(Dispatchers.Main) { _chats.value = arrayChats }
                            }
                            */
                        }
                    }

                    viewModelScope.launch(Dispatchers.IO) {
                        for (temporalChat in temporalChats){
                            Log.e(">>>", temporalChat.toString())
                            val tempFriendId = temporalChat.friendId
                            val tempIdMessage = temporalChat.idLastMessage

                            var msg = Firebase.firestore.collection("chats").document(tempFriendId)
                                .collection("room").document(userId!!)
                                .collection("messages").document(tempIdMessage).get().await()

                            val message = msg.get("text") as String
                            val dateLong = msg.get("date") as Long

                            val user = Firebase.firestore.collection("residents")
                                .document(tempFriendId).get().await()

                            val name = user.get("name") as String

                            var chat = ChatItem(tempFriendId, tempIdMessage, name, message, dateLong)
                            arrayChats.add(chat)
                            withContext(Dispatchers.Main) { _chats.value = arrayChats }
                        }
                    }
                }
        }
    }

    fun updateChats() {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("chats")
                .addSnapshotListener { data, e ->
                    for (doc in data!!.documentChanges) {
                        if (doc.type.name == "MODIFIED") {
                            var chatId = doc.document.id
                            for (chat in arrayChats) {
                                if (chat.friendId == chatId) {
                                    chat.friendId = chatId
                                }
                            }
                        }
                    }
                }
        }
    }
}
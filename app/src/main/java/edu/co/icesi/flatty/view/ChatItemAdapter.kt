package edu.co.icesi.flatty.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.FragmentChatResidentBinding
import edu.co.icesi.flatty.model.Chat
import edu.co.icesi.flatty.model.ChatItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatItemAdapter: RecyclerView.Adapter<ChatItemView>() {

    private val chatItemList = ArrayList<ChatItem>()

    /*init {
        chatItemList.add(ChatItem(UUID.randomUUID().toString(),UUID.randomUUID().toString(),"Miriam Londoño", "Hola", 1))
        chatItemList.add(ChatItem(UUID.randomUUID().toString(),UUID.randomUUID().toString(),"Camilo Londoño", "Gracias", 2))
        chatItemList.add(ChatItem(UUID.randomUUID().toString(),UUID.randomUUID().toString(),"Andres Londoño", "Adios", 3))
        notifyItemRangeInserted(0,3)
    }*/

    fun addChat(chat: ChatItem){
        chatItemList.add(chat)
        notifyItemInserted(itemCount-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemView {
        var inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.chat_item, parent, false)
        val itemView = ChatItemView(item)
        return itemView
    }

    override fun onBindViewHolder(skeleton: ChatItemView, position: Int) {
        val chatItem = chatItemList[position]
        skeleton.chatItemNameTV.text = chatItem.name
        skeleton.chatItemMessageTV.text = chatItem.message
        Firebase.storage.getReference().child("profile").child(chatItem.profilePhoto).downloadUrl.addOnSuccessListener {
            Glide.with(skeleton.chatItemImage).load(it).into(skeleton.chatItemImage)
        }
        var date = Date(chatItem.lastMessageHour)
        var format = SimpleDateFormat("HH:mm aa")
        skeleton.chatItemLastMessageHourTV.text = format.format(date)
        skeleton.chatLayout.setOnClickListener {
            val intent = Intent(skeleton.chatLayout.context, ChatPageGuard::class.java).apply {
                putExtra("residentId",chatItem.friendId)
                putExtra("residentName",chatItem.name)
            }
            startActivity(skeleton.chatLayout.context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return chatItemList.size
    }

    fun clear() {
        val count = chatItemList.count()
        chatItemList.clear()
        notifyItemRangeRemoved(0,count)
    }

}
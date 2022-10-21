package edu.co.icesi.flatty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.ChatItem

class ChatItemAdapter: RecyclerView.Adapter<ChatItemView>() {

    private val chatItemList = ArrayList<ChatItem>()

    init {
        chatItemList.add(ChatItem("Miriam Londoño", "Hola", "11:08"))
        chatItemList.add(ChatItem("Camilo Londoño", "Gracias", "10:08"))
        chatItemList.add(ChatItem("Andres Londoño", "Adios", "09:08"))
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
        skeleton.chatItemLastMessageHourTV.text = chatItem.lastMessageHour
    }

    override fun getItemCount(): Int {
        return chatItemList.size
    }

}
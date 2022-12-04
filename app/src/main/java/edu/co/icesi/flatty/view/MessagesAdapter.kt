package edu.co.icesi.flatty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.Message
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val messages = ArrayList<Message>()

    fun addMensaje(mensaje: Message) {
        messages.add(mensaje)
        notifyItemInserted(messages.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        lateinit var skeleton: RecyclerView.ViewHolder
        when(viewType){
            0->{
                skeleton = OwnChatMessageHolder(inflater.inflate(R.layout.message_container, parent, false))
            }
            1->{
                skeleton = OtherChatMessageHolder(inflater.inflate(R.layout.message_received_container, parent, false))
            }
        }
        return skeleton
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position].author == Firebase.auth.currentUser?.uid) {
            return 0;
        }else{
            return 1;
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when(holder.itemViewType){
            0->{
                val skeleton = holder as OwnChatMessageHolder
                skeleton.messageTV.text = message.text
                var date = Date(message.date)
                var format = SimpleDateFormat("HH:mm aa")
                skeleton.hourMessageTv.text = format.format(date)
            }
            1->{
                val skeleton = holder as OtherChatMessageHolder
                skeleton.messageTV.text = message.text
                var date = Date(message.date)
                var format = SimpleDateFormat("HH:mm aa")
                skeleton.hourMessageTv.text = format.format(date)
            }
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun clear() {
        val count = messages.count()
        messages.clear()
        notifyItemRangeRemoved(0,count)
    }
}
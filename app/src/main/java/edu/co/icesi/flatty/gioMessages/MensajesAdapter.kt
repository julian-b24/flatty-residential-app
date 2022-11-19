package edu.co.icesi.flatty.gioMessages

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.Message
import edu.co.icesi.flatty.quejas.Queja
import edu.co.icesi.flatty.quejas.QuejaView
import edu.co.icesi.flatty.viewModel.ChatResidentViewModel
import edu.co.icesi.flatty.viewModel.LoginPageViewModel

class MensajesAdapter : RecyclerView.Adapter<MensajeView>() {

    private val mensajes = ArrayList<Message>()


    init {

    /*mensajes.add(Mensaje("Hola, buen día", TypeShows.RECIBIDO))
        mensajes.add(Mensaje("Llegó un domicilio de Rappi", TypeShows.RECIBIDO))
        mensajes.add(Mensaje("Vale! Gracias", TypeShows.ENVIADO))
        mensajes.add(Mensaje("Dicen que no está pago", TypeShows.RECIBIDO))
        mensajes.add(Mensaje("Ya bajo", TypeShows.ENVIADO))*/
    }

    fun initMessage(messages:ArrayList<Message>){
        mensajes.addAll(messages)
        notifyItemRangeInserted(0,itemCount)
    }

    fun addMensaje(mensaje: Message) {
        mensajes.add(mensaje)
        notifyItemInserted(mensajes.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.message_row, parent, false)
        return MensajeView(row)
    }

    override fun onBindViewHolder(holder: MensajeView, position: Int) {
        val message = mensajes[position]
        holder.twMensaje.text = message.text
        if(message.author != Firebase.auth.currentUser!!.uid){
            holder.twMensaje.setBackgroundColor(Color.parseColor("#EDEDED"))
        }
        /*if(mensaje.typeShow == TypeShows.RECIBIDO) {
            holder.twMensaje.setBackgroundColor(Color.parseColor("#EDEDED"))
        }*/
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }

    fun clear() {
        val count = mensajes.count()
        mensajes.clear()
        notifyItemRangeRemoved(0,count)
    }
}
package edu.co.icesi.flatty.gioMessages

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.quejas.Queja
import edu.co.icesi.flatty.quejas.QuejaView

class MensajesAdapter : RecyclerView.Adapter<MensajeView>() {

    private val mensajes = ArrayList<Mensaje>()

    init {
        mensajes.add(Mensaje("Hola, buen día", TypeShows.RECIBIDO))
        mensajes.add(Mensaje("Llegó un domicilio de Rappi", TypeShows.RECIBIDO))
        mensajes.add(Mensaje("Vale! Gracias", TypeShows.ENVIADO))
        mensajes.add(Mensaje("Dicen que no está pago", TypeShows.RECIBIDO))
        mensajes.add(Mensaje("Ya bajo", TypeShows.ENVIADO))
    }

    fun addMensaje(mensaje: Mensaje) {
        mensajes.add(mensaje)
        notifyItemInserted(mensajes.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.message_row, parent, false)
        return MensajeView(row)
    }

    override fun onBindViewHolder(holder: MensajeView, position: Int) {
        val mensaje = mensajes[position]
        holder.twMensaje.text = mensaje.text
        if(mensaje.typeShow == TypeShows.RECIBIDO) {
            holder.twMensaje.setBackgroundColor(Color.parseColor("#EDEDED"))
        }
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }
}
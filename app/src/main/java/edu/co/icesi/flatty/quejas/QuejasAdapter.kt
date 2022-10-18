package edu.co.icesi.flatty.quejas

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import java.text.SimpleDateFormat

class QuejasAdapter : RecyclerView.Adapter<QuejaView>() {

    private val quejas = ArrayList<Queja>()

    // Quejas quemadas para las pruebas
    init {
        quejas.add(Queja("Daño tubería baño", SimpleDateFormat("dd/MM/yyyy").parse("16/11/2022"), States.PENDIENTE))
        quejas.add(Queja("Vecinos ruidosos", SimpleDateFormat("dd/MM/yyyy").parse("02/08/2022"), States.PENDIENTE))
        quejas.add(Queja("Mal olor zona verde", SimpleDateFormat("dd/MM/yyyy").parse("15/07/2022"), States.FINALIZDO))
        quejas.add(Queja("Sonido de ratas", SimpleDateFormat("dd/MM/yyyy").parse("10/07/2022"), States.PENDIENTE))
        quejas.add(Queja("Niños molestando", SimpleDateFormat("dd/MM/yyyy").parse("01/07/2022"), States.PENDIENTE))
    }

    fun addQueja(queja: Queja) {
        quejas.add(queja)
        notifyItemInserted(quejas.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuejaView {

        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.queja_row, parent, false)
        return QuejaView(row)

    }

    override fun onBindViewHolder(holder: QuejaView, position: Int) {
        val queja = quejas[position]
        holder.quejaTitleRow.text = queja.title
        holder.quejaCreatedOnRow.text = queja.createdOn.day.toString() + "/" + queja.createdOn.month.toString() + "/" + queja.createdOn.year.toString()
        holder.quejaStatusRow.text = queja.status.status
        if(queja.status == States.FINALIZDO) {
            holder.quejaImageStatus.setImageResource(R.drawable.gio_rectangle_completed_status)
        } else {
            holder.quejaImageStatus.setImageResource(R.drawable.gio_rectangle_status)
        }
    }

    override fun getItemCount(): Int {
        return quejas.size
    }

}
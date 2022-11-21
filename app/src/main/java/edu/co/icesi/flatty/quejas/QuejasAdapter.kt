package edu.co.icesi.flatty.quejas

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import java.text.SimpleDateFormat

class QuejasAdapter : RecyclerView.Adapter<QuejaView>() {

    private val quejas = ArrayList<Queja>()

    fun clearQuejasList()
    {
        quejas.clear();
    }

    fun addQueja(queja: Queja) {
        quejas.add(queja)
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
        holder.quejaDescriptionRow.text = queja.description
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
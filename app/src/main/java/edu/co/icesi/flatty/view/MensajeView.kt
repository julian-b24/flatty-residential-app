package edu.co.icesi.flatty.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R

class MensajeView(itemView:View) : RecyclerView.ViewHolder(itemView) {
    var tvContentMSG : TextView = itemView.findViewById(R.id.tvContentMSG)
}
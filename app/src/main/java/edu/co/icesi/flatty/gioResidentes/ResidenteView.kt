package edu.co.icesi.flatty.gioResidentes

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R

class ResidenteView(itemView:View) : RecyclerView.ViewHolder(itemView) {
    var txtResidentName : TextView = itemView.findViewById(R.id.txtResidentName)
    var txtResidentPhone : TextView = itemView.findViewById(R.id.txtResidentPhone)
    var btnVerResidente : Button = itemView.findViewById(R.id.btnVerResidente)
}
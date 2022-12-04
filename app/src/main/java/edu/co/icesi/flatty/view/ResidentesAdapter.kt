package edu.co.icesi.flatty.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.Resident

class ResidentesAdapter : RecyclerView.Adapter<ResidenteView>() {

    private val residentes = ArrayList<Resident>()

    fun resetResidentsList()
    {
        residentes.clear()
    }

    fun addResident(resident: Resident)
    {
        residentes.add(resident)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidenteView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.residente_row, parent, false)
        return ResidenteView(row)
    }

    override fun onBindViewHolder(holder: ResidenteView, position: Int) {
        val residente = residentes[position]
        holder.txtResidentName.text = residente.name
        holder.txtResidentPhone.text = residente.phone
        Log.e(">>>", "View loades")
        holder.btnVerResidente.setOnClickListener {
            val intent = Intent(holder.btnVerResidente.context, ProfileSearchedPage::class.java).apply {
                putExtra("residentId", residente.id)
            }
            startActivity(holder.btnVerResidente.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return residentes.size
    }
}
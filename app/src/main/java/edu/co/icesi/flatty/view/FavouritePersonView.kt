package edu.co.icesi.flatty.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R

class FavouritePersonView(itemView: View): RecyclerView.ViewHolder(itemView) {
    var personNameTV : TextView = itemView.findViewById(R.id.personNameTV)
    var idDocumentTV : TextView = itemView.findViewById(R.id.idDocumentTV)
}
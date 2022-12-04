package edu.co.icesi.flatty.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R

class QuejaView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var quejaTitleRow : TextView = itemView.findViewById(R.id.twQuejaTitle)
    var quejaCreatedOnRow : TextView = itemView.findViewById(R.id.twCreatedOnQueja)
    var quejaStatusRow : TextView = itemView.findViewById(R.id.twQuejaStatus)
    val quejaImageStatus : ImageView = itemView.findViewById(R.id.iwStatusBackground)
    val quejaDescriptionRow : TextView = itemView.findViewById(R.id.txtQuejaDescription)

}
package edu.co.icesi.flatty.view

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R

class ChatItemView(itemView: View): RecyclerView.ViewHolder(itemView) {
    var chatItemNameTV: TextView = itemView.findViewById(R.id.chatItemNameTV)
    var chatItemMessageTV: TextView = itemView.findViewById(R.id.chatItemMessageTV)
    var chatItemLastMessageHourTV: TextView = itemView.findViewById(R.id.chatItemLastMessageHourTV)
    var chatLayout: ConstraintLayout = itemView.findViewById(R.id.clChat)
}

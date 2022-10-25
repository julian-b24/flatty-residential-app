package edu.co.icesi.flatty.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R

class FavouriteVehicleView(itemView: View): RecyclerView.ViewHolder(itemView) {
    var vehicleModelTV : TextView = itemView.findViewById(R.id.vehicleModelTV)
    var licensePlateTV : TextView = itemView.findViewById(R.id.licensePlateTV)
    var vehicleIcon: ImageView = itemView.findViewById(R.id.vehicleIcon)
}
package edu.co.icesi.flatty.view

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.FavouriteVehicle
import edu.co.icesi.flatty.model.VehicleType
import java.io.File

class FavouriteVehicleAdapter : RecyclerView.Adapter<FavouriteVehicleView>(){

    private val favouriteVehicleList = ArrayList<FavouriteVehicle>()

    init{
        favouriteVehicleList.add(FavouriteVehicle("Mazda 3", "ECX 564", VehicleType.CAR))
        favouriteVehicleList.add(FavouriteVehicle("Raider 125 TVS", "HGI 29B", VehicleType.MOTORCYCLE))
        favouriteVehicleList.add(FavouriteVehicle("Nissan March", "JOP 100", VehicleType.CAR))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteVehicleView {
        var inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.favourite_vehicle_item, parent, false)
        val itemView = FavouriteVehicleView(item)
        return itemView
    }

    override fun onBindViewHolder(skeleton: FavouriteVehicleView, position: Int) {
        val favouriteVehicle = favouriteVehicleList[position]
        skeleton.vehicleModelTV.text = favouriteVehicle.model
        skeleton.licensePlateTV.text = favouriteVehicle.licensePlate
        skeleton.vehicleIcon.setImageResource(R.drawable.car_blue)

        if(favouriteVehicle.vehicleType == VehicleType.MOTORCYCLE){
            Log.e(">>>", "AQUÍ")
            skeleton.vehicleIcon.setImageResource(R.drawable.two_wheeler_blue)
        }
    }

    override fun getItemCount(): Int {
        return favouriteVehicleList.size
    }


}
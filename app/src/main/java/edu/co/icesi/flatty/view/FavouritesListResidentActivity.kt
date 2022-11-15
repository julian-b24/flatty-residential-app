package edu.co.icesi.flatty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityFavouritesListResidentBinding
import edu.co.icesi.flatty.databinding.AddVehicleBottomSheetBinding
import edu.co.icesi.flatty.model.VehicleType

class FavouritesListResidentActivity : AppCompatActivity() {

    val binding:ActivityFavouritesListResidentBinding by lazy{
        ActivityFavouritesListResidentBinding.inflate(layoutInflater)
    }

    private lateinit var layoutManagerFavouritePerson: LinearLayoutManager
    private lateinit var adapterFavouritePerson: FavouritePersonAdapter

    private lateinit var layoutManagerFavouriteVehicle: LinearLayoutManager
    private lateinit var adapterFavouriteVehicle: FavouriteVehicleAdapter

    private var typeNewVehicle: VehicleType = VehicleType.CAR


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        layoutManagerFavouritePerson = GridLayoutManager(this,2)
        binding.personsRV.layoutManager = layoutManagerFavouritePerson
        binding.personsRV.setHasFixedSize(true)
        adapterFavouritePerson = FavouritePersonAdapter()
        binding.personsRV.adapter = adapterFavouritePerson

        layoutManagerFavouriteVehicle = GridLayoutManager(this, 2)
        binding.vehiclesRV.layoutManager = layoutManagerFavouriteVehicle
        binding.vehiclesRV.setHasFixedSize(true)
        adapterFavouriteVehicle = FavouriteVehicleAdapter()
        binding.vehiclesRV.adapter = adapterFavouriteVehicle

        binding.addPersonBtn.setOnClickListener{
            val bottomSheetDialog = BottomSheetDialog(
                this@FavouritesListResidentActivity, R.style.BottomSheetDialogThee)

            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
                R.layout.add_person_bottom_sheet,
                findViewById(R.id.addPersonBottomSheet) as LinearLayout?
            )

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        binding.addVehicleBtn.setOnClickListener{
            val bottomSheetDialog = BottomSheetDialog(
                this@FavouritesListResidentActivity, R.style.BottomSheetDialogThee)

            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
                R.layout.add_vehicle_bottom_sheet,
                findViewById(R.id.addVehicleBottomSheet) as LinearLayout?
            )

            bottomSheetView.findViewById<ImageButton>(R.id.newCarBtn).setOnClickListener {
                typeNewVehicle = VehicleType.CAR

                bottomSheetView.findViewById<ImageButton>(R.id.newCarBtn).setImageResource(R.drawable.car_blue)
                bottomSheetView.findViewById<ImageButton>(R.id.newCarBtn).setBackgroundResource(R.drawable.blue_square)

                bottomSheetView.findViewById<ImageButton>(R.id.newMotorcycleBtn).setImageResource(R.drawable.two_wheeler_gray)
                bottomSheetView.findViewById<ImageButton>(R.id.newMotorcycleBtn).setBackgroundResource(R.drawable.gray_square)
            }

            bottomSheetView.findViewById<ImageButton>(R.id.newMotorcycleBtn).setOnClickListener {
                typeNewVehicle = VehicleType.MOTORCYCLE

                bottomSheetView.findViewById<ImageButton>(R.id.newMotorcycleBtn).setImageResource(R.drawable.two_wheeler_blue)
                bottomSheetView.findViewById<ImageButton>(R.id.newMotorcycleBtn).setBackgroundResource(R.drawable.blue_square)

                bottomSheetView.findViewById<ImageButton>(R.id.newCarBtn).setImageResource(R.drawable.directions_car_gray)
                bottomSheetView.findViewById<ImageButton>(R.id.newCarBtn).setBackgroundResource(R.drawable.gray_square)
            }

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()

        }

        binding.backLYT.setOnClickListener {
            finish()
        }


    }
}
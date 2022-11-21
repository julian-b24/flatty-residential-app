package edu.co.icesi.flatty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityFavouritesListResidentBinding
import edu.co.icesi.flatty.model.FavouritePerson
import edu.co.icesi.flatty.model.FavouriteVehicle
import edu.co.icesi.flatty.model.VehicleType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FavouritesListResidentActivity : AppCompatActivity() {

    val residentId: String = Firebase.auth.currentUser?.uid.toString()

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

        getResidentFavourites()

        binding.addPersonBtn.setOnClickListener{
            val bottomSheetDialog = BottomSheetDialog(
                this@FavouritesListResidentActivity, R.style.BottomSheetDialogThee)

            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
                R.layout.add_person_bottom_sheet,
                findViewById(R.id.addPersonBottomSheet) as LinearLayout?
            )

            bottomSheetView.findViewById<Button>(R.id.add_new_personBtn).setOnClickListener {
                val name = bottomSheetView.findViewById<TextView>(R.id.newPersonNameET).text.toString()
                val number = bottomSheetView.findViewById<TextView>(R.id.newPersonIdET).text.toString()

                if(name.isNotEmpty() && number.isNotEmpty()){
                    addPersonToFavourites(name, number)

                    bottomSheetView.findViewById<TextView>(R.id.newPersonNameET).text = ""
                    bottomSheetView.findViewById<TextView>(R.id.newPersonIdET).text = ""
                }else{
                    Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
                }
            }

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
                val model = bottomSheetView.findViewById<TextView>(R.id.newVehicleModelET).text.toString()
                val licensePlate = bottomSheetView.findViewById<TextView>(R.id.newVehicleLicensePlateET).text.toString()
                if (model.isNotEmpty()&&licensePlate.isNotEmpty()){
                    addVehicleToFavourites(model, licensePlate)
                    bottomSheetView.findViewById<TextView>(R.id.newVehicleModelET).text = ""
                    bottomSheetView.findViewById<TextView>(R.id.newVehicleLicensePlateET).text = ""

                }else{
                    Toast.makeText(this,"Campos vacíos", Toast.LENGTH_LONG).show()
                }


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

    private fun addVehicleToFavourites(model: String, licensePlate: String) {
        lifecycleScope.launch(Dispatchers.IO){

            val newVehicleReference = Firebase.firestore.collection("residents")
                .document(residentId)
                .collection("vehicles")
                .document()

            val newVehicleId = newVehicleReference.id
            val newVehicle = FavouriteVehicle(newVehicleId, model, licensePlate)

            newVehicleReference.set(newVehicle)

            withContext(Dispatchers.Main){
                adapterFavouriteVehicle.addFavouriteVehicle(newVehicle)
                Toast.makeText(this@FavouritesListResidentActivity, "Nuevo vehiculo añadido", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun addPersonToFavourites(name: String, number: String) {
        lifecycleScope.launch(Dispatchers.IO){

            val newPersonReference = Firebase.firestore.collection("residents")
                .document(residentId)
                .collection("persons")
                .document()

            val newPersonId = newPersonReference.id
            val newPerson = FavouritePerson(newPersonId, name, number)

            newPersonReference.set(newPerson)

            withContext(Dispatchers.Main){
                adapterFavouritePerson.addFavouritePerson(newPerson)
                Toast.makeText(this@FavouritesListResidentActivity, "Nueva persona añadida", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun getResidentFavourites() {
        lifecycleScope.launch(Dispatchers.IO){

            //Request documents from Firebase
            val resultVehicles = Firebase.firestore
                .collection("residents")
                .document(residentId)
                .collection("vehicles")
                .get()
                .await()

            val resultPersons = Firebase.firestore
                .collection("residents")
                .document(residentId)
                .collection("persons")
                .get()
                .await()

            //Parse documents into list
            var vehicles = arrayListOf<FavouriteVehicle>()
            for (doc in resultVehicles){
                val vehicle = doc.toObject(FavouriteVehicle::class.java)
                vehicles.add(vehicle)
            }

            var persons = arrayListOf<FavouritePerson>()
            for (doc in resultPersons){
                val person = doc.toObject(FavouritePerson::class.java)
                persons.add(person)
            }

            withContext(Dispatchers.Main){
                setResidentFavourites(vehicles, persons)
            }
        }
    }

    private fun setResidentFavourites(vehicles: ArrayList<FavouriteVehicle>, persons: ArrayList<FavouritePerson>){
        adapterFavouritePerson = FavouritePersonAdapter()
        adapterFavouriteVehicle = FavouriteVehicleAdapter()

        adapterFavouritePerson.favouritePersonsList = persons
        adapterFavouriteVehicle.favouriteVehicleList = vehicles

        layoutManagerFavouritePerson = GridLayoutManager(this,2)
        binding.personsRV.layoutManager = layoutManagerFavouritePerson
        binding.personsRV.setHasFixedSize(true)
        binding.personsRV.adapter = adapterFavouritePerson

        layoutManagerFavouriteVehicle = GridLayoutManager(this, 2)
        binding.vehiclesRV.layoutManager = layoutManagerFavouriteVehicle
        binding.vehiclesRV.setHasFixedSize(true)
        binding.vehiclesRV.adapter = adapterFavouriteVehicle

    }
}
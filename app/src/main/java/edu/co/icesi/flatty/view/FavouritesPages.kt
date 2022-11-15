package edu.co.icesi.flatty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityFavouritesPagesBinding
import edu.co.icesi.flatty.databinding.ActivityProfileSearchedPageBinding
import edu.co.icesi.flatty.model.FavouritePerson
import edu.co.icesi.flatty.model.FavouriteVehicle
import edu.co.icesi.flatty.model.Resident
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FavouritesPages : AppCompatActivity() {

    lateinit var residentId: String
    lateinit var resident: Resident

    private val binding: ActivityFavouritesPagesBinding by lazy{
        ActivityFavouritesPagesBinding.inflate(layoutInflater)
    }

    private lateinit var layoutManagerFavouritePerson: LinearLayoutManager
    private lateinit var adapterFavouritePerson: FavouritePersonAdapter

    private lateinit var layoutManagerFavouriteVehicle: LinearLayoutManager
    private lateinit var adapterFavouriteVehicle: FavouriteVehicleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        residentId = intent.extras?.getString("residentId").toString()
        getResident(residentId)

        binding.btnBackToSearchedPage.setOnClickListener {
           finish()
        }

    }

    private fun getResident(residentId: String){
        lifecycleScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore
                .collection("residents")
                .document(residentId)
                .get()
                .await()

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

            Log.e(">>>", result.toString())
            resident = result.toObject(Resident::class.java)!!

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
                setResidentsName(resident)
                setResidentFavourites(vehicles, persons)
            }
        }
    }

    private fun setResidentsName(resident: Resident){
        binding.nameResidentTV.text = resident.name
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
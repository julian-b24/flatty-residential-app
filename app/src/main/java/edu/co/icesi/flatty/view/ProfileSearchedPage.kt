package edu.co.icesi.flatty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.databinding.ActivityProfileSearchedPageBinding
import edu.co.icesi.flatty.model.FavouritePerson
import edu.co.icesi.flatty.model.FavouriteVehicle
import edu.co.icesi.flatty.model.Resident
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProfileSearchedPage : AppCompatActivity() {

    val residentId: String = "PzDvLwtTnDeswT5yOzo3"
    lateinit var resident: Resident

    private lateinit var binding: ActivityProfileSearchedPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSearchedPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getResident(residentId)

        binding.btnBackToSearchResident.setOnClickListener {
            finish()
        }

        binding.goToFavoritos.setOnClickListener{
            var intent = Intent(this, FavouritesPages::class.java)
            startActivity(intent)
        }

        binding.favouritesLyt.setOnClickListener {
            var intent = Intent(this, FavouritesPages::class.java)
            startActivity(intent)
        }

    }

    private fun getResident(residentId: String){
        lifecycleScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore
                .collection("residents")
                .document(residentId)
                .get()
                .await()

            resident = result.toObject(Resident::class.java)!!

            val numberAparment = resident.numberApartment.slice(IntRange(0, 2))
            val towerApartment = resident.numberApartment[3].toString()

            withContext(Dispatchers.Main){
                binding.nameSearchedResidentTV.text = resident.name
                binding.emailSearchedResidentTV.text = resident.email
                binding.ageTV.text = resident.age
                binding.PhoneNumberTV.text = resident.phone
                binding.ApartamentNumberTV.text = numberAparment
                binding.ApartmentTowerTV.text = towerApartment

            }

        }
    }
}
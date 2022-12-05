package edu.co.icesi.flatty.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.set
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.co.icesi.flatty.databinding.ActivityEditProfileBinding
import edu.co.icesi.flatty.model.Resident
import edu.co.icesi.flatty.model.Queja
import java.util.*

class EditProfile : AppCompatActivity() {

    lateinit var uriImage : Uri
    var profileImageChanged = false

    private val binding: ActivityEditProfileBinding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    fun downloadImage(photoID:String?)
    {
        Firebase.storage.getReference().child("profile").child(photoID!!).downloadUrl.addOnSuccessListener {
            Glide.with(binding.residentProfilePicIV).load(it).into(binding.residentProfilePicIV)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        val query = Firebase.firestore.collection("residents").document(Firebase.auth.uid!!)
        query.get().addOnCompleteListener {
            val resident = it.result.toObject(Resident::class.java)
            val numberAparment = resident!!.numberApartment.slice(IntRange(1, 3))
            val towerApartment = resident!!.numberApartment[0].toString()
            binding.residentNameET.setText(resident!!.name)
            binding.residentEmailET.setText(resident!!.email)
            binding.residentPhoneET.setText(resident!!.phone)
            binding.residentAparmentET.setText(numberAparment)
            binding.residentTowerET.setText(towerApartment)
            binding.residentAgeET.setText(resident!!.age)
            downloadImage(resident!!.profilePhoto)
        }

        binding.backLYT.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            var name = binding.residentNameET.text
            var email = binding.residentEmailET.text
            var phone = binding.residentPhoneET.text
            var apartment = binding.residentAparmentET.text
            var tower = binding.residentTowerET.text
            var age = binding.residentAgeET.text
            var apartmentAndTower = tower.append(apartment)
            if(profileImageChanged)
            {
                val filename = UUID.randomUUID().toString()
                Firebase.storage.getReference().child("profile").child(filename).putFile(uriImage!!)
                Firebase.firestore.collection("residents").document(Firebase.auth.uid!!)
                    .update("profilePhoto", filename)
            }
            Firebase.firestore.collection("residents").document(Firebase.auth.uid!!)
                .update("age", age.toString(), "email", email.toString(),
                    "name", name.toString(), "phone", phone.toString(), "numberApartment", apartmentAndTower.toString())
            finish()
        }

        binding.addProfilePictureBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }
    }

    fun onGalleryResult(result: ActivityResult)
    {
        if(result.resultCode == RESULT_OK)
        {
            uriImage = result.data?.data!!
            profileImageChanged = true
            uriImage?.let {
                binding.residentProfilePicIV.setImageURI(uriImage)
            }
        }
    }
}
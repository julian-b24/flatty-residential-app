package edu.co.icesi.flatty.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import edu.co.icesi.flatty.MainActivity
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.FragmentResidentProfileBinding
import edu.co.icesi.flatty.model.Resident
import edu.co.icesi.flatty.viewModel.AuthResult
import edu.co.icesi.flatty.viewModel.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ResidentProfileFragment : Fragment() {

    private var _binding: FragmentResidentProfileBinding? = null
    private val binding get() = _binding!!
    lateinit var residentId: String
    lateinit var resident: Resident

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        residentId = Firebase.auth.currentUser!!.uid
        Log.e(">>>", residentId)
        getResident(residentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResidentProfileBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.favouritesLYT.setOnClickListener {
            startActivity(Intent(binding.root.context, FavouritesListResidentActivity::class.java))
        }

        binding.editProfileLYT.setOnClickListener {
            startActivity(Intent(binding.root.context, EditProfile::class.java))
        }

        binding.logoutBtn.setOnClickListener {
            startActivity(Intent(binding.root.context,LoginPageResident::class.java))
        }

        binding.logoutBtn.setOnClickListener{
            activity?.finish()
        }

        return view
    }
    private fun getResident(residentId: String){
        lifecycleScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore
                .collection("residents")
                .document(residentId)
                .get()
                .await()

            resident = result.toObject(Resident::class.java)!!

            val numberAparment = resident.numberApartment.slice(IntRange(1, 3))
            val towerApartment = resident.numberApartment[0].toString()

            withContext(Dispatchers.Main){
                binding.residentNameTV.text = resident.name
                binding.emailResidentTV.text = resident.email
                binding.ageResident.text = resident.age
                binding.phoneResident.text = resident.phone
                binding.aptoResident.text = numberAparment
                binding.towerResident.text = towerApartment
                downloadImage(resident.profilePhoto)
            }

        }
    }

    fun downloadImage(photoID:String?)
    {
        Firebase.storage.getReference().child("profile").child(photoID!!).downloadUrl.addOnSuccessListener {
            Glide.with(binding.imageView3).load(it).into(binding.imageView3)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResidentProfileFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
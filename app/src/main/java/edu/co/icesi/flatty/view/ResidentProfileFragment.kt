package edu.co.icesi.flatty.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import edu.co.icesi.flatty.MainActivity
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.FragmentResidentProfileBinding
import edu.co.icesi.flatty.model.Resident
import edu.co.icesi.flatty.viewModel.AuthResult
import edu.co.icesi.flatty.viewModel.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResidentProfileFragment : Fragment() {

    private var _binding: FragmentResidentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            //logout activity
        }

        return view
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
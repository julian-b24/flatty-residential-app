package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivitySearchResidentBinding
import edu.co.icesi.flatty.databinding.FragmentSearchResidentBinding
import edu.co.icesi.flatty.gioResidentes.ResidentesAdapter
import edu.co.icesi.flatty.model.Resident


class SearchResidentFragment : Fragment() {

    private lateinit var binding: FragmentSearchResidentBinding

    // Lógica Residentes
    private lateinit var adapter : ResidentesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    var residentString : String = "residente(s)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResidentBinding.inflate(inflater,container,false)
        val view = binding.root
        // Inflate the layout for this fragment

        // Lógica Residentes
        layoutManager = LinearLayoutManager(context)
        binding.ResidentesRecycler.layoutManager = layoutManager
        binding.ResidentesRecycler.setHasFixedSize(true)
        adapter = ResidentesAdapter()
        binding.ResidentesRecycler.adapter = adapter
        binding.txtInputSearchApartment.addTextChangedListener {
            var apartment = binding.txtInputSearchApartment.text.toString().uppercase()
            Log.e(">>>", "Apartment to search in Firebase: " + apartment)
            val query = Firebase.firestore.collection("residents").whereEqualTo("numberApartment", apartment)
            query.get().addOnCompleteListener {
                if(!it.result.isEmpty)
                {
                    adapter.resetResidentsList()
                    for(resident in it.result!!)
                    {
                        val resident = resident.toObject(Resident::class.java)
                        adapter.addResident(resident)
                    }
                    binding.apartmentNumberLabel.text = apartment
                    adapter.notifyDataSetChanged()
                    binding.labelNumeroResidentes2.text = adapter.itemCount.toString()+" "+residentString
                }
                else
                {
                    binding.apartmentNumberLabel.text = "No se encontro el apartamento "+apartment
                    binding.labelNumeroResidentes2.text = "0 "+residentString
                }
            }
        }

        binding.btnBackLogOut.setOnClickListener {
            var intent = Intent(binding.root.context,LoginPageResident::class.java)
            startActivity(intent)
        }
//        val btnGoToResident: Button  = view.findViewById(R.id.btnGoToResident1)
//        val btnBackLogOut: Button  = view.findViewById(R.id.btnBackLogOut)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchResidentFragment()
    }
}

private operator fun Any.plus(s: String): CharSequence? {
    return s;
}

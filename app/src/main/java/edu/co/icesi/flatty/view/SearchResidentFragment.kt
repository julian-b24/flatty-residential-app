package edu.co.icesi.flatty.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivitySearchResidentBinding
import edu.co.icesi.flatty.databinding.FragmentSearchResidentBinding


class SearchResidentFragment : Fragment() {
    private lateinit var binding: FragmentSearchResidentBinding
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
        binding.btnGoToResident1.setOnClickListener {
            var intent = Intent(binding.root.context, ProfileSearchedPage::class.java)
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
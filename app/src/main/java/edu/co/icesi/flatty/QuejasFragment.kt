package edu.co.icesi.flatty

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.flatty.databinding.FragmentQuejasBinding
import edu.co.icesi.flatty.quejas.QuejasAdapter
import edu.co.icesi.flatty.view.CreateComplaint
import edu.co.icesi.flatty.view.LoginPageResident

class QuejasFragment : Fragment() {
    private var _binding: FragmentQuejasBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : QuejasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuejasBinding.inflate(inflater,container,false)
        val view = binding.root
        layoutManager = LinearLayoutManager(binding.root.context)
        binding.QuejasRecycler.layoutManager = layoutManager
        binding.QuejasRecycler.setHasFixedSize(true)
        adapter = QuejasAdapter()
        binding.QuejasRecycler.adapter = adapter
        binding.imageView9.setOnClickListener {
            var intent = Intent(binding.root.context, CreateComplaint::class.java)
            startActivity(intent)
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuejasFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
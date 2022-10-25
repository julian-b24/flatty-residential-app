package edu.co.icesi.flatty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.flatty.databinding.FragmentChatResidentBinding
import edu.co.icesi.flatty.gioMessages.Mensaje
import edu.co.icesi.flatty.gioMessages.MensajesAdapter
import edu.co.icesi.flatty.gioMessages.TypeShows

class ChatResidentFragment : Fragment() {

    private var _binding: FragmentChatResidentBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : MensajesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChatResidentBinding.inflate(inflater, container, false)
        val view = binding.root
        layoutManager = LinearLayoutManager(binding.root.context)
        binding.ChatRecycler.layoutManager = layoutManager
        binding.ChatRecycler.setHasFixedSize(true)
        adapter = MensajesAdapter()
        binding.ChatRecycler.adapter = adapter


        binding.ivBtnAddMessage.setOnClickListener {
            val mensaje = Mensaje(binding.tpMessage.text.toString(), TypeShows.ENVIADO)
            adapter.addMensaje(mensaje)
            binding.tpMessage.text.clear()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChatResidentFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
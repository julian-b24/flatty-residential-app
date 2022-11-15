package edu.co.icesi.flatty.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.databinding.FragmentChatResidentBinding
import edu.co.icesi.flatty.gioMessages.Mensaje
import edu.co.icesi.flatty.gioMessages.MensajesAdapter
import edu.co.icesi.flatty.gioMessages.TypeShows
import edu.co.icesi.flatty.model.Chat
import edu.co.icesi.flatty.model.Message
import edu.co.icesi.flatty.viewModel.ChatResidentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ChatResidentFragment : Fragment() {

    private var _binding: FragmentChatResidentBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MensajesAdapter
    private val viewModel: ChatResidentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

        viewModel.subcribeToMessage()

        /*
        viewModel.messages.observe(this){
            if(it.size >= 1) {
                val m = it.last()
                adapter.addMensaje(m)
            }
        }*/

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
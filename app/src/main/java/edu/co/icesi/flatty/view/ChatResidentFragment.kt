package edu.co.icesi.flatty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.databinding.FragmentChatResidentBinding
import edu.co.icesi.flatty.gioMessages.MensajesAdapter
import edu.co.icesi.flatty.model.Message
import edu.co.icesi.flatty.viewModel.ChatResidentViewModel
import java.util.*

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
            val msg = Message(
                UUID.randomUUID().toString(),
                Firebase.auth.currentUser!!.uid,
                Date().time,
                binding.tpMessage.text.toString()
            )
            //Enviar mensaje a la db
            viewModel.saveMsg(msg)
            binding.tpMessage.setText("")
        }

        viewModel.subcribeToMessage()


        viewModel.messages.observe(viewLifecycleOwner){
            adapter.clear()
            for(msg in it.messages) {
                adapter.addMensaje(msg)
            }

        }

        /*
        viewModel.messages.observe(this){

        }*/

        return view
    }

    fun init() {
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
package edu.co.icesi.flatty.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.FragmentChatListBinding
import edu.co.icesi.flatty.model.Queja
import edu.co.icesi.flatty.model.Resident
import edu.co.icesi.flatty.viewModel.ChatResidentViewModel
import edu.co.icesi.flatty.viewModel.ChatsGuardViewModel

class ChatGuardFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter :ChatItemAdapter
    private val viewModel: ChatsGuardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        val view = binding.root
        layoutManager = LinearLayoutManager(binding.root.context)
        binding.ChatRecycler.layoutManager = layoutManager
        binding.ChatRecycler.setHasFixedSize(true)
        adapter = ChatItemAdapter()
        binding.ChatRecycler.adapter = adapter

        viewModel.suscribeToChats()
        viewModel.updateChats()

        viewModel.chats.observe(viewLifecycleOwner){
            Log.e(">>>", "Chats Observed:")
            Log.e(">>>", it.toString())
            adapter.clear()
            for(chat in it) {
                Log.e(">>>",chat.toString())
                val query = Firebase.firestore.collection("residents").document(chat.friendId)
                query.get().addOnCompleteListener {
                    val resident = it.result.toObject(Resident::class.java)
                    chat.profilePhoto = resident!!.profilePhoto
                }
                adapter.addChat(chat)
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChatGuardFragment()
    }
}
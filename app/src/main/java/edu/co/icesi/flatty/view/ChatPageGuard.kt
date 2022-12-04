package edu.co.icesi.flatty.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.co.icesi.flatty.databinding.ActivityChatPageGuardBinding
import edu.co.icesi.flatty.model.Message
import edu.co.icesi.flatty.model.Resident
import edu.co.icesi.flatty.viewModel.ChatPageGuardViewModel
import java.util.*

class ChatPageGuard : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MessagesAdapter
    private val viewModel: ChatPageGuardViewModel by viewModels()

    private val binding: ActivityChatPageGuardBinding by lazy {
        ActivityChatPageGuardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)

        binding.chatRecyclerGC.layoutManager = layoutManager
        binding.chatRecyclerGC.setHasFixedSize(true)
        adapter = MessagesAdapter()
        binding.chatRecyclerGC.adapter = adapter

        val residentId = intent.extras?.getString("residentId")
        val name = intent.extras?.getString("residentName")

        binding.twProfileNameGC.text = name
        val query = Firebase.firestore.collection("residents").document(residentId!!)
        query.get().addOnCompleteListener {
            val resident = it.result.toObject(Resident::class.java)
            Firebase.storage.getReference().child("profile").child(resident!!.profilePhoto).downloadUrl.addOnSuccessListener {
                Glide.with(binding.ivProfilePhotoGC).load(it).into(binding.ivProfilePhotoGC)
            }
        }

        viewModel.subcribeToMessage(residentId!!)

        viewModel.messages.observe(this){
            adapter.clear()
            for(msg in it.messages) {
                adapter.addMensaje(msg)
            }
        }

        binding.ivBtnAddMessageGC.setOnClickListener {
            val msg = Message(
                UUID.randomUUID().toString(),
                Firebase.auth.currentUser!!.uid,
                Date().time,
                binding.tpMessageGC.text.toString()
            )
            //Enviar mensaje a la db
            viewModel.saveMsg(msg,residentId)
            binding.tpMessageGC.setText("")
        }

    }
}
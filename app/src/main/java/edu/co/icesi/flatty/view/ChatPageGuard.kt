package edu.co.icesi.flatty.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.flatty.databinding.ActivityChatPageGuardBinding
import edu.co.icesi.flatty.model.Message
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
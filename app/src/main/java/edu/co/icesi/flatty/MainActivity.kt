package edu.co.icesi.flatty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.flatty.databinding.ActivityMainBinding
import edu.co.icesi.flatty.gioMessages.Mensaje
import edu.co.icesi.flatty.gioMessages.MensajesAdapter
import edu.co.icesi.flatty.gioMessages.TypeShows
import edu.co.icesi.flatty.quejas.QuejasAdapter
import kotlinx.android.synthetic.main.chat_page.*
import kotlinx.android.synthetic.main.quejas_page.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : QuejasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quejas_page)

        layoutManager = LinearLayoutManager(this)

        QuejasRecycler.layoutManager = layoutManager
        QuejasRecycler.setHasFixedSize(true)
        adapter = QuejasAdapter()
        QuejasRecycler.adapter = adapter

        /*ivBtnAddMessage.setOnClickListener {
            val mensaje = Mensaje(tpMessage.text.toString(), TypeShows.ENVIADO)
            adapter.addMensaje(mensaje)
            tpMessage.text.clear()
        }*/
    }
}
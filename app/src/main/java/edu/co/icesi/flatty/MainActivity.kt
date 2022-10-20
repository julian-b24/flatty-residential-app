package edu.co.icesi.flatty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import edu.co.icesi.flatty.databinding.ActivityResidentProfilePageBinding
import edu.co.icesi.flatty.databinding.LoginPageResidentBinding
import edu.co.icesi.flatty.databinding.WelcomePageBinding
import edu.co.icesi.flatty.view.LoginPageResident
import edu.co.icesi.flatty.view.ResidentProfilePage


class MainActivity : AppCompatActivity() {

    /*private lateinit var binding: WelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startActivity(Intent(this@MainActivity, SearchResident::class.java))
        setContentView(R.layout.quejas_page)

        /*ivBtnAddMessage.setOnClickListener {
            val mensaje = Mensaje(tpMessage.text.toString(), TypeShows.ENVIADO)
            adapter.addMensaje(mensaje)
            tpMessage.text.clear()
        }*/

        binding = WelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this,LoginPageResident::class.java)
            startActivity(intent)
        }
    }*/

    private lateinit var binding:WelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this, LoginPageResident::class.java)
            startActivity(intent)
        }
    }
}
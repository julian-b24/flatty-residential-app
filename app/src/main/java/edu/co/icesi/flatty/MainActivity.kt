package edu.co.icesi.flatty

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import edu.co.icesi.flatty.databinding.WelcomePageBinding
import edu.co.icesi.flatty.view.LoginPageResident


class MainActivity : AppCompatActivity() {

    /*private lateinit var binding: ActivityMainBinding

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

        /*binding = WelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this,LoginPageResident::class.java)
            startActivity(intent)
        }*/
    }*/

    private lateinit var binding:WelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions(arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
        ), 1)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this, LoginPageResident::class.java)
            startActivity(intent)
        }
    }
}
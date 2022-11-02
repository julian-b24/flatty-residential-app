package edu.co.icesi.flatty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import edu.co.icesi.flatty.QuejasFragment
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityResidentProfilePageBinding
import edu.co.icesi.flatty.databinding.ActivitySearchResidentBinding
import edu.co.icesi.flatty.databinding.LoginPageResidentBinding
import edu.co.icesi.flatty.model.Resident


class ResidentProfilePage : AppCompatActivity() {

    private  val binding: ActivityResidentProfilePageBinding by lazy {
        ActivityResidentProfilePageBinding.inflate(layoutInflater)
    }
    private lateinit var quejasFragment: QuejasFragment
    private lateinit var residentProfileFragment: ResidentProfileFragment
    private lateinit var chatResidentFragment: ChatResidentFragment
    private lateinit var resident: Resident

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val resident = loadResident()
        if(Firebase.auth.currentUser == null){
            startActivity(Intent(this, LoginPageResident::class.java))
            finish()
            return
        }else{
            //this.resident = resident
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG)
        }

        quejasFragment = QuejasFragment.newInstance()
        residentProfileFragment = ResidentProfileFragment.newInstance()
        chatResidentFragment = ChatResidentFragment.newInstance()

        showFragment(quejasFragment)
        binding.navigator2.setOnItemSelectedListener { menuItem->
            if (menuItem.itemId == R.id.homeItem){
                showFragment(quejasFragment)
            } else if(menuItem.itemId == R.id.profileItem) {
                showFragment(residentProfileFragment)
            } else if(menuItem.itemId == R.id.chatItem){
                showFragment(chatResidentFragment)
            }
            true
        }
    }
    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.residentFragmentContainer,fragment)
        transaction.commit()
    }

    fun loadResident(): Resident?{
        val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
        val json = sp.getString("user", "NO_USER")
        if(json == "NO_USER"){
            return null
        }else{
            return Gson().fromJson(json, Resident::class.java)
        }
    }

    fun logout(){
        finish()
        startActivity(Intent(this,LoginPageResident::class.java))
        //Firebase.messaging.unsubscribeFromTopic(resident.id)
        val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
        sp.edit().clear().apply()
        Firebase.auth.signOut()
    }
}
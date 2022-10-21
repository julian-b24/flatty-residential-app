package edu.co.icesi.flatty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.co.icesi.flatty.QuejasFragment
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityResidentProfilePageBinding
import edu.co.icesi.flatty.databinding.ActivitySearchResidentBinding
import edu.co.icesi.flatty.databinding.LoginPageResidentBinding


class ResidentProfilePage : AppCompatActivity() {

    private  val binding: ActivityResidentProfilePageBinding by lazy {
        ActivityResidentProfilePageBinding.inflate(layoutInflater)
    }
    private lateinit var quejasFragment: QuejasFragment
    private lateinit var residentProfileFragment: ResidentProfileFragment
    private lateinit var chatResidentFragment: ChatResidentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

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
}
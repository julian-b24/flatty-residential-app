package edu.co.icesi.flatty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.databinding.ActivityFavouritesPagesBinding
import edu.co.icesi.flatty.databinding.ActivitySearchResidentBinding

class SearchResident : AppCompatActivity() {
    private val binding: ActivitySearchResidentBinding by lazy {
        ActivitySearchResidentBinding.inflate(layoutInflater)
    }
    private lateinit var searchResidentFragment: SearchResidentFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        searchResidentFragment = SearchResidentFragment.newInstance()
        showFragment(searchResidentFragment)
        binding.navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.itemHome) {
                showFragment(searchResidentFragment)
            }
//            if(menuItem.itemId == R.id.itemHome){
//                showFragment(searchResidentFragment)
//            }else if(menuItem.itemId == R.id.itemChat){
//                showFragment("nombre del fragmento del chat")
//            }
            true
        }




    }
    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }
}
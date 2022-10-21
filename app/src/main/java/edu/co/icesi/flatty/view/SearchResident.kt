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
import edu.co.icesi.flatty.databinding.FragmentChatListBinding

class SearchResident : AppCompatActivity() {

    private val binding: ActivitySearchResidentBinding by lazy {
        ActivitySearchResidentBinding.inflate(layoutInflater)
    }
    private lateinit var searchResidentFragment: SearchResidentFragment
    private lateinit var chatGuardFragment: ChatGuardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        searchResidentFragment = SearchResidentFragment.newInstance()
        chatGuardFragment = ChatGuardFragment.newInstance()
        showFragment(searchResidentFragment)
        binding.navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.itemHome) {
                showFragment(searchResidentFragment)
            }else if(menuItem.itemId == R.id.itemChat){
                showFragment(chatGuardFragment)
            }
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
package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment())
                .commit()
        }


        binding.bnvMain.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.menu_home -> {
                        HomeFragment()
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_search -> {
                        SearchFragment()
                        return@setOnItemSelectedListener true
                    }
                    else -> {
                        GalleryFragment()
                        return@setOnItemSelectedListener true
                    }
                }

            )
            false
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


}

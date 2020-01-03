package com.erikriosetiawan.moviecatalogue.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.erikriosetiawan.moviecatalogue.R
import com.erikriosetiawan.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        loadFragment(MovieFragment())
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            var fragment: Fragment? = null

            when (it.itemId) {
                R.id.item_movie -> fragment = MovieFragment.newInstance()
                R.id.item_tv_show -> fragment = TvShowFragment.newInstance()
            }
            return@setOnNavigationItemSelectedListener loadFragment(fragment)
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.frameLayout.id, fragment)
                .commit()
            return true
        }
        return false
    }
}

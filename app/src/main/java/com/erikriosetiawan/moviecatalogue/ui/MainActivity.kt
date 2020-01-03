package com.erikriosetiawan.moviecatalogue.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_change_language -> {
                val changeLanguageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(changeLanguageIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

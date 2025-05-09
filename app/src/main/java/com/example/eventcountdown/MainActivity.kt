package com.example.eventcountdown

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Set default fragment to HomeFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commit()
        }

        // Handle navigation item selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            // Animate selected item
            val view = bottomNavigationView.findViewById<View>(item.itemId)
            val anim = AnimationUtils.loadAnimation(this, R.anim.nav_item_scale)
            view?.startAnimation(anim)

            // Fragment switching
            val selectedFragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_add_event -> AddEventFragment()
                R.id.nav_important -> ImportantEventsFragment()
                else -> null
            }

            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,  // enter animation
                        android.R.anim.fade_out  // exit animation
                    )
                    .replace(R.id.fragmentContainer, it)
                    .commit()
            }

            true
        }

    }
}

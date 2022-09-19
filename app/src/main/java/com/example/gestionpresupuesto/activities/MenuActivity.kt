package com.example.gestionpresupuesto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gestionpresupuesto.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

    }
}
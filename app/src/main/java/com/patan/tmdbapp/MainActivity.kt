package com.patan.tmdbapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.patan.tmdbapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createBottomNavigation()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomnav, navController)

        binding.bottomnav.setOnItemSelectedListener { item ->
            val myBundle = Bundle()
            when (item.itemId) {
                R.id.homeFragment -> {
                    myBundle.putString("topath", "now_playing")
                    navController.navigate(R.id.HomeFragment, myBundle)
                }
                R.id.popularFragment -> {
                    myBundle.putString("topath", "popular")
                    navController.navigate(R.id.HomeFragment, myBundle)
                }
                R.id.ratedFragment -> {
                    myBundle.putString("topath", "top_rated")
                    navController.navigate(R.id.HomeFragment, myBundle)
                }
                R.id.upcomingFragment -> {
                    myBundle.putString("topath", "upcoming")
                    navController.navigate(R.id.HomeFragment, myBundle)
                }
                R.id.searchFragment ->{
                    navController.navigate(R.id.searchFragment)
                }
                else -> return@setOnItemSelectedListener false
            }
            true
        }
    }

    private fun createBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomnav, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

package com.patan.tmdbapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.patan.tmdbapp.databinding.ActivityMainBinding
import com.patan.tmdbapp.ui.Fragments.HomeFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createBottomNavigation()
        auth = FirebaseAuth.getInstance()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomnav, navController)

        binding.bottomnav.setOnItemSelectedListener { item ->
            val myBundle = Bundle()
            fun control(keyword: String?) {
                myBundle.putString("topath", keyword)
                navController.navigate(R.id.HomeFragment, myBundle)
            }
            when (item.itemId) {
                R.id.homeFragment -> {
                    control("now_playing")
                }

                R.id.popularFragment -> {
                    control("popular")
                }

                R.id.ratedFragment -> {
                    control("top_rated")
                }

                R.id.upcomingFragment -> {
                    control("upcoming")
                }

                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                }

                else -> return@setOnItemSelectedListener false
            }
            true
        }
        binding.userSettings.setOnClickListener {
            fab()
        }
        binding.favMovies.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUserSettingsFragment()
            navController.navigate(action)
        }
    }

    private fun fab() {
        if (!binding.favMovies.isVisible) {
            binding.favMovies.isVisible = true
        } else {
            binding.favMovies.isVisible = false
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

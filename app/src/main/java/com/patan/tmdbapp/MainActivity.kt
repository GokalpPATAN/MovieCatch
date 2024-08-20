package com.patan.tmdbapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.patan.tmdbapp.databinding.ActivityMainBinding
import com.patan.tmdbapp.ui.ViewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createBottomNavigation()

        binding.apply {
            bottomnav.setOnItemSelectedListener { item ->
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
            userSettings.setOnClickListener {
                viewModel.fabVisibility()
            }
            favMovies.setOnClickListener {
                val action = NavGraphDirections.actionGlobalUserSettingsFragment()
                navController.navigate(action)
            }
            quit.setOnClickListener {
                viewModel.signOut()
                val pref = getSharedPreferences("userInfos", Context.MODE_PRIVATE)
                val editor = pref?.edit()
                editor?.clear()
                editor?.apply()
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                this@MainActivity.startActivity(intent)
            }
        }

        viewModel.fabVisible.observe(this) {
            binding.favMovies.isVisible = it
            binding.quit.isVisible = it
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

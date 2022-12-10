package com.dr.udaan

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dr.udaan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var mDrawerLayout: DrawerLayout
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        navHostFragment =
            (supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment?)!!
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout

        navigate()
        navView()
        actions()
        setUpNavComponent()
        setBottomNavigation()
        destinationControl()
        setContentView(binding.root)
    }

    private fun setUpNavComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun destinationControl() {
        navController.addOnDestinationChangedListener { _, navDestination, _ ->

            when (navDestination.id) {
                R.id.home -> {
                    binding.bottomNevigation.visibility = View.VISIBLE
                    binding.menu.visibility = View.VISIBLE
                    binding.search.visibility = View.VISIBLE
                    binding.notification.visibility = View.VISIBLE
                }

                R.id.library -> {
                    binding.bottomNevigation.visibility = View.VISIBLE
                    binding.menu.visibility = View.VISIBLE
                    binding.search.visibility = View.VISIBLE
                    binding.notification.visibility = View.VISIBLE
                }

                R.id.exam -> {
                    binding.bottomNevigation.visibility = View.VISIBLE
                    binding.menu.visibility = View.VISIBLE
                    binding.search.visibility = View.VISIBLE
                    binding.notification.visibility = View.VISIBLE
                }

                R.id.profile -> {
                    binding.bottomNevigation.visibility = View.VISIBLE
                    binding.menu.visibility = View.VISIBLE
                    binding.search.visibility = View.VISIBLE
                    binding.notification.visibility = View.VISIBLE
                }

                else -> {
                    binding.bottomNevigation.visibility = View.GONE
                    binding.menu.visibility = View.GONE
                    binding.search.visibility = View.GONE
                    binding.notification.visibility = View.GONE
                }
            }
        }
    }

    private fun navigate(): Boolean {
        val page = intent.getStringExtra("page") ?: ""
        if (page == "login") {
            navController.navigate(R.id.login)
            return true
        }
        else if (page == "register") {
            navController.navigate(R.id.register)
             return true
        }
        return false
    }

    fun navView(){
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.exam -> {
                   navController.navigate(R.id.exam)
                    true
                }
                R.id.library -> {
                    navController.navigate(R.id.library)
                    true
                }
                R.id.settings -> {
                    navController.navigate(R.id.setting)
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.support -> {
                    Toast.makeText(this, "support", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setBottomNavigation() {
        binding.bottomNevigation.setupWithNavController(navController)
    }

    private fun actions() {
        binding.menu.setOnClickListener {

            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            else {
                drawerLayout.openDrawer(GravityCompat.START, true)
            }
        }
        binding.notification.setOnClickListener(){
            navController.navigate(R.id.notification)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}
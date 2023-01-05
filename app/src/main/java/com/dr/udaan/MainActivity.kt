package com.dr.udaan

import android.app.Activity
import android.os.Bundle
import android.util.Log
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
import com.dr.udaan.MyApp.Companion.razorPayPaymentData
import com.dr.udaan.databinding.ActivityMainBinding
import com.dr.udaan.room.UserData
import com.dr.udaan.ui.BaseActivity
import com.razorpay.Checkout
import com.razorpay.PayloadHelper
import org.json.JSONObject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun init() {

        navHostFragment =
            (supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment?)!!
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout

        Checkout.preload(this)

        destinationControl()

        if (navigate()) {
            return
        }

        razorPayPaymentData.observeForever {
            if (it.isStartPayment) {
                startPayment()
            }
        }

        navView()
        actions()
        setUpNavComponent()
        setBottomNavigation()

    }


    private fun setUpNavComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun destinationControl() {
        navController.addOnDestinationChangedListener { _, navDestination, _ ->

            Log.d("TAG", "destinationControl: ${navDestination.displayName}")
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

    private fun navView(){
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

    private fun startPayment() {
        /*
        *  You need to pass the current activity to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("<YOUR_KEY_ID>")

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","50000")//pass amount in currency subunits

            val retryObj = JSONObject()
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)

        } catch (e: Exception) {
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)


}
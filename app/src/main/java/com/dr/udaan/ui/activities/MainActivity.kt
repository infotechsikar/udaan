package com.dr.udaan.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.dr.udaan.MyApp.Companion.razorPayPaymentData
import com.dr.udaan.R
import com.dr.udaan.databinding.ActivityMainBinding
import com.dr.udaan.base.BaseActivity
import com.dr.udaan.ui.fragments.NoInternet
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.Const
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.razorpay.Checkout
import org.json.JSONObject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    companion object {
        var bottomNavigationView: BottomNavigationView? = null
    }

    override fun init() {

        bottomNavigationView = binding.bottomNevigation

        /**
         * When Internet is not available then navigate to No Internet Page
         */
        if (!AppFunctions.checkConnection(this)) {
            startActivity(Intent(mContext, NoInternet::class.java))
            finish()
            return
        }

        navHostFragment =
            (supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment?)!!
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout

        Checkout.preload(this)

        destinationControl()

        razorPayPaymentData.observeForever {
            if (it.isStartPayment) {
                startPayment()
            }
        }

        Handler(Looper.getMainLooper()).post {
            navView()
            actions()
            setUpNavComponent()
            setBottomNavigation()
        }

    }

    private fun setUpNavComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun destinationControl() {
        navController.addOnDestinationChangedListener { _, navDestination, args ->

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

//            if (args != null) {
//                val isSearch = args.getBoolean(Const.IS_SEARCH, false)
//                if (isSearch) {
//                    binding.bottomNevigation.visibility = View.GONE
//                    binding.menu.setOnClickListener {
//                        binding.search.clearFocus()
//                        navController.popBackStack()
//                    }
//                    binding.menu.load(R.drawable.ic_baseline_arrow_back_ios_24)
//                }
//            } else {
//                binding.menu.load(R.drawable.ic_menu)
//                actions()
//            }

        }
    }

    private fun navView(){
        binding.navigationView.itemIconTintList = null
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.exam -> {
                    binding.bottomNevigation.selectedItemId = R.id.exam
                    // navController.navigate(R.id.exam)
                    true
                }
                R.id.library -> {
                    binding.bottomNevigation.selectedItemId = R.id.library
                    //navController.navigate(R.id.library)
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
                R.id.contactUs -> {
                    navController.navigate(R.id.contactUs)
                    true
                }
                R.id.aboutUs -> {
                    startActivity(
                        Intent(
                            mContext,
                            MyWebViewer::class.java
                        ).apply {
                            putExtra(Const.CONTENT, AppFunctions.getAboutUs(mContext))
                            putExtra(Const.TITLE, "About us")
                        }
                    )
                    true
                }
                R.id.privacyPolicy -> {
                    startActivity(
                        Intent(
                            mContext,
                            MyWebViewer::class.java
                        ).apply {
                            putExtra(Const.CONTENT, AppFunctions.getPrivacyPolicy(mContext))
                            putExtra(Const.TITLE, "Privacy Policy")
                        }
                    )
                    true
                }
                R.id.termsConditions -> {
                    startActivity(
                        Intent(
                            mContext,
                            MyWebViewer::class.java
                        ).apply {
                            putExtra(Const.CONTENT, AppFunctions.getTermsAndConditions(mContext))
                            putExtra(Const.TITLE, "Terms & Conditions")
                        }
                    )
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
            Handler(Looper.getMainLooper()).post {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START, true)
                }
            }
        }

        binding.notification.setOnClickListener(){
            navController.navigate(R.id.notification)
        }

        binding.search.setOnClickListener {
            val args = Bundle()
            args.putBoolean(Const.IS_SEARCH, true)
            navController.navigate(R.id.tests, args)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
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
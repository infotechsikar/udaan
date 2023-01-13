package com.dr.udaan.authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dr.udaan.MainActivity
import com.dr.udaan.base.BaseActivity
import com.dr.udaan.databinding.ActivitySplashBinding
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.Const

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val handler = Handler(Looper.getMainLooper())
    lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDelay()
        actions()
    }

    private fun setDelay() {
        runnable = Runnable {
            if(AppFunctions.isUserVerified(this))  {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startAnimation()
            }
        }
        handler.postDelayed(
            runnable, if(AppFunctions.isUserVerified(this)) 2000 else 300
        )
    }

    private fun startAnimation() {
        binding.llLogoAppName.animate().translationY(-150f).duration = 1000
        binding.slogan.animate().translationY(-150f).duration = 1000
        binding.slogan.animate().alpha(1f).duration = 1000
        binding.backgroundImage.animate().alpha(0f).duration = 1000
        binding.llLoginBtn.animate().alpha(1f).duration = 1000
    }

    private fun actions() {

        binding.login.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
        binding.signup.setOnClickListener {
            startActivity(Intent(this, Register::class.java).apply {
                putExtra(Const.IS_FROM_SPLASH, true)
            })
            finish()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (this::runnable.isInitialized)
            handler.removeCallbacks(runnable)
    }

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

}
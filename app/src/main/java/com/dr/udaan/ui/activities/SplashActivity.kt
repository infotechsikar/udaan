package com.dr.udaan.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.view.isVisible
import com.dr.udaan.authentication.Login
import com.dr.udaan.authentication.Register
import com.dr.udaan.base.BaseActivity
import com.dr.udaan.databinding.ActivitySplashBinding
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.Const

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val handler = Handler(Looper.getMainLooper())
    lateinit var runnable: Runnable

    override fun init() {
        setDelay()
        actions()
    }

    private fun setDelay() {
        if(AppFunctions.isUserVerified())  {
            binding.backgroundImage.isVisible = false
        }
        runnable = Runnable {
            if(AppFunctions.isUserVerified())  {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startAnimation()
            }
        }
        handler.postDelayed(
            runnable, if(AppFunctions.isUserVerified()) 2000 else 300
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
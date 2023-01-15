package com.dr.udaan.ui.fragments

import androidx.activity.OnBackPressedCallback
import com.dr.udaan.base.BaseActivity
import com.dr.udaan.databinding.ActivityNoInternetBinding
import kotlin.system.exitProcess

class NoInternet : BaseActivity<ActivityNoInternetBinding>() {

    override fun init() {
        actions()
    }

    private fun actions() {

        binding.okay.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

    }

    override fun getViewBinding() = ActivityNoInternetBinding.inflate(layoutInflater)

}
package com.dr.udaan.authentication

import com.dr.udaan.databinding.ActivityForgotPasswordBinding
import com.dr.udaan.base.BaseActivity

class ForgotPassword : BaseActivity<ActivityForgotPasswordBinding>() {

    private fun action() {

        binding.otp.setOnClickListener{
            if (binding.phone.text.toString().trim().isEmpty()){
                binding.phone.error = "Enter your phone number or email address"
                return@setOnClickListener
            }
           // Navigation.findNavController(binding.root).navigate(R.id.otpForgotPassword)
        }

    }


    override fun getViewBinding(): ActivityForgotPasswordBinding {
         return ActivityForgotPasswordBinding.inflate(layoutInflater)
    }

    override fun init() {
        action()
    }

}
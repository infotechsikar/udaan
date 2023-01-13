package com.dr.udaan.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.MainActivity
import com.dr.udaan.R
import com.dr.udaan.api.retrofit.AllRequest.LoginRequest
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.api.retrofit.Retrofitinstance.getRetrofit
import com.dr.udaan.databinding.ActivityLoginBinding
import com.dr.udaan.room.MyDatabase
import com.dr.udaan.ui.BaseActivity
import com.dr.udaan.ui.BaseFragment
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class Login : BaseActivity<ActivityLoginBinding>() {

    private fun action() {

        binding.login.setOnClickListener {

            if (binding.phone.text.toString().trim().isEmpty()){
                binding.phone.error = "Enter your phone number"
                return@setOnClickListener
            }

            if (binding.passwords.text.toString().trim().isEmpty()){
                binding.passwords.error = "Enter your passwords here"
                return@setOnClickListener
            }

            if (binding.passwords.text.toString().length < 8){
                binding.passwords.error = "Enter at least 8 Characters"
                return@setOnClickListener
            }
            val mobileNo = binding.phone.text.toString()
            val password = binding.passwords.text.toString()

            showLoading()
            CoroutineScope(IO).launch {
                try {
                    val response = getRetrofit().login(mobileNo,password).await()
                    withContext(Main) {
                        dismissLoading()
                        if (response.success == true && response.userData != null) {
                            try {
                                AppFunctions.setUserVerified(mContext)
                                MyDatabase.getDatabase(mContext)
                                    .userData().insert(response.userData!!)
                                startActivity(
                                    Intent(
                                        mContext, MainActivity::class.java
                                    )
                                )
                                finish()
                            } catch (e: Exception){
                                e.printStackTrace()
                            }
                        } else {
                            Toast.makeText(mContext, response.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Main) {
                        dismissLoading()
                        Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        binding.forgotPassword.setOnClickListener() {
            startActivity(
                Intent(
                    mContext, ForgotPassword::class.java
                )
            )
        }

        binding.signup.setOnClickListener(){
            startActivity(
                Intent(
                    mContext, Register::class.java
                )
            )
            finish()
        }
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun init() {
        action()
    }

}
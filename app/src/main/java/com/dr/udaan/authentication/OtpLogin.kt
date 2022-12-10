package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentOtpLoginBinding
import com.dr.udaan.retrofit.AllRequest.VerifyOtpRequest
import com.dr.udaan.retrofit.Retrofitinstance
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class OtpLogin : Fragment() {
    lateinit var binding:FragmentOtpLoginBinding
    lateinit var mContext: Context
    lateinit var otp: String
    lateinit var userid:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentOtpLoginBinding.inflate(layoutInflater)
        otp = arguments?.getString("otp").toString()
        userid = arguments?.getString("userid").toString()
        action()
        return binding.root
    }

    private fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }

        binding.signup.setOnClickListener{
            val etOTP = binding.otp.text.toString()

            if (otp != etOTP) {
                Snackbar.make(binding.root, "Incorrect OTP",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            CoroutineScope(IO).launch {
                verifyOtp(userid, etOTP)
            }
        }

        binding.login.setOnClickListener(){
            findNavController().navigate(R.id.login)
        }

        // Disable Resend button by default
        val cd = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Change time value in resend button
            }

            override fun onFinish() {
                 // Enable Resend button
            }
        }
        cd.start()
    }

    private suspend fun verifyOtp(userId: String, otp: String) {
        val request = VerifyOtpRequest(
            otp, userId
        )

        val response = Retrofitinstance.getRetrofit().verifyOtp(request).await()

        withContext(Main) {
            if (response.success == true ){
                Toast.makeText(mContext, "OTP Verified", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.login)
            }
            else {
                Toast.makeText(mContext, "Incorrect OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun resendOtp(){

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
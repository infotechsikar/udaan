package com.dr.udaan.authentication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentOtpLoginBinding
import com.dr.udaan.databinding.FragmentRegisterBinding
import com.dr.udaan.retrofit.AllRequest.RegisterRequest
import com.dr.udaan.retrofit.AllRequest.ResendOtpRequest
import com.dr.udaan.retrofit.AllRequest.VerifyOtpRequest
import com.dr.udaan.retrofit.Retrofitinstance
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
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
    lateinit var bindingR: FragmentRegisterBinding

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

            val mobileNO = bindingR.phone.text.toString().trim()
            val password = bindingR.passwords.text.toString().trim()

            CoroutineScope(IO).
            launch {
                register(mobileNO, password)
            }
        }

        binding.login.setOnClickListener(){
            findNavController().navigate(R.id.login)
        }

        // Disable Resend button by default
        val cd = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Change time value in resend button
                binding.resend.setOnClickListener(){
                    binding.resend.text = "" + millisUntilFinished / 1000
                }
            }
            override fun onFinish() {
                 // Enable Resend button
                binding.resend.text = ""
            }
        }
        cd.start()
    }


    private suspend fun register(mobileNO: String, password: String) {

        val request = RegisterRequest(
            "1", mobileNO, password, password
        )

        try {
            val response = Retrofitinstance.getRetrofit().register(request).await()

            Log.d("!!!_-->", "register: ${response.success}   ${response.otpStatus}")

            if (response.success == false) {
                withContext(Main) {
                    if (response.message == "Your mobile no is already registered.! Please Login") {
                        findNavController().navigate(R.id.login)
                    } else {
                        Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    return@withContext
                }
                return
            }

            if (response.otpStatus == false) {
                val otp = response.otp
                val userId = response.userId
                val bundle = Bundle()
                Log.d("!!!_-->", "register: OTP $otp")

                withContext(Main) {
                    bundle.putString("userid", userId.toString())
                    bundle.putString("otp", otp.toString())
                    //  Toast.makeText(mContext, "OTP $otp", Toast.LENGTH_SHORT).show()
                    //  findNavController().navigate(R.id.otpLogin)
                    findNavController().navigate(R.id.otpLogin, bundle)
                }
            }
        }
        catch (e: Exception) {
            Log.d("!!!_-->", "register: ${e.message}")
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
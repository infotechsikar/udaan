package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentRegisterBinding
import com.dr.udaan.retrofit.AllRequest.RegisterRequest
import com.dr.udaan.retrofit.Retrofitinstance.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class Register : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    private fun action() {
        binding.back.setOnClickListener() {
            findNavController().popBackStack()
        }
        binding.continues.setOnClickListener {
            if (binding.phone.text.toString().trim().isEmpty()) {
                binding.phone.error = "Enter your phone number"
                return@setOnClickListener
            }
            if (binding.passwords.text.toString().trim().isEmpty()) {
                binding.passwords.error = "Enter your passwords here"
                return@setOnClickListener
            }

            val mobileNO = binding.phone.text.toString().trim()
            val password = binding.passwords.text.toString().trim()

            CoroutineScope(IO)
                .launch {
                    register(mobileNO, password)
                }
//            Navigation.findNavController(binding.root).navigate(R.id.otpLogin)
        }

        binding.login.setOnClickListener() {
            findNavController().navigate(R.id.login)
        }
    }

    /**
     *  Transfer OTP to Verify Page and verify
     */

    private suspend fun register(mobileNO: String, password: String) {

        val request = RegisterRequest(
            "1", mobileNO, password, password
        )

        try {
            val response = getRetrofit().register(request).await()

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
        } catch (e: Exception) {
            Log.d("!!!_-->", "register: ${e.message}")
        }

//        getRetrofit().register(
//            request
//        ).enqueue(object : Callback<RegisterResponse> {
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
//                 val res = response.body()
//                if (res != null) {
//                    Log.d(TAG, "onResponse: " + res.otp)
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//
//            }
//        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}


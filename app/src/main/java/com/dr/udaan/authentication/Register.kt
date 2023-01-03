package com.dr.udaan.authentication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentRegisterBinding
import com.dr.udaan.ui.BaseFragment
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class Register : BaseFragment<FragmentRegisterBinding>() {

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    companion object {
        var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        callBacks()
        action()
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
            if (binding.password.text.toString().trim().isEmpty())
            if (binding.password.text.toString().length != 8){
                binding.password.error = "Enter Atleast 8 Characters"
                return@setOnClickListener
            }

            val mobileNO = binding.phone.text.toString().trim()

            showLoading()
            sendOtp("+91$mobileNO")
        }

        binding.login.setOnClickListener() {
            findNavController().navigate(R.id.login)
        }
    }

    private fun sendOtp(phoneNumber: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity as AppCompatActivity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun callBacks() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                dismissLoading()
            }
            override fun onVerificationFailed(e: FirebaseException) {

                dismissLoading()

                try {

                    when (e) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            Toast.makeText(mContext, "Invalid request", Toast.LENGTH_SHORT).show()
                        }
                        is FirebaseTooManyRequestsException -> {
                            Toast.makeText(mContext, "Too much requests", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                catch (e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            )
            {
                dismissLoading()
                storedVerificationId = verificationId
                resendToken = token
                val bundle = Bundle()
                bundle.putString("phone", binding.phone.text.toString())
                bundle.putString("password", binding.password.text.toString())
                bundle.putString("verificationId", storedVerificationId)
                findNavController().navigate(R.id.otpLogin, bundle)
            }
        }
    }

    override fun getViewBinding() = FragmentRegisterBinding.inflate(layoutInflater)

}


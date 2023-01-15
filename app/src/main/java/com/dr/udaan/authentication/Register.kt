package com.dr.udaan.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.dr.udaan.databinding.ActivityRegisterBinding
import com.dr.udaan.base.BaseActivity
import com.dr.udaan.util.Const
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class Register : BaseActivity<ActivityRegisterBinding>() {

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun init() {
        auth = Firebase.auth
        callBacks()
        action()

        binding.back.isVisible = !(intent != null && intent.getBooleanExtra(Const.IS_FROM_SPLASH, false))

    }

    companion object {
        var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    }

    private fun action() {

        binding.back.setOnClickListener() {
            startActivity(
                Intent(
                    mContext, Login::class.java
                )
            )
            finish()
        }

        binding.continues.setOnClickListener {

            if (binding.phone.text.toString().trim().isEmpty()) {
                binding.phone.error = "Enter your phone number"
                return@setOnClickListener
            }
            if (binding.password.text.toString().trim().isEmpty()) {
                binding.password.error = "Enter your passwords here"
                return@setOnClickListener
            }
            if (binding.password.text.toString().length < 8) {
                binding.password.error = "Enter at least 8 Characters"
                return@setOnClickListener
            }

            val mobileNO = binding.phone.text.toString().trim()

            if (Const.TEST_MODE) {
                // For testing
                val bundle = Bundle()
                bundle.putString("phone", binding.phone.text.toString())
                bundle.putString("password", binding.password.text.toString())
                bundle.putString("verificationId", storedVerificationId)
                startActivity(
                    Intent(
                        mContext, Login::class.java
                    )
                )
            } else {
                showLoading()
                sendOtp("+91$mobileNO")
            }


        }

        binding.login.setOnClickListener() {
            startActivity(
                Intent(
                    mContext, Login::class.java
                )
            )
        }
    }

    private fun sendOtp(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
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
                            Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                dismissLoading()
                storedVerificationId = verificationId
                resendToken = token
                startActivity(
                    Intent(
                        mContext, OtpLogin::class.java
                    ).apply {
                        putExtra(Const.PHONE, binding.phone.text.toString())
                        putExtra(Const.PASSWORD, binding.password.text.toString())
                        putExtra(Const.VERIFICATION_ID, storedVerificationId)
                    }
                )
            }
        }
    }

    override fun onBackPressed() {
        startActivity(
            Intent(
                mContext, Login::class.java
            )
        )
        finish()
        super.onBackPressed()
    }

    override fun getViewBinding() = ActivityRegisterBinding.inflate(layoutInflater)


}


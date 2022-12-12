package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.authentication.Register.Companion.resendToken
import com.dr.udaan.databinding.FragmentOtpLoginBinding
import com.dr.udaan.databinding.FragmentRegisterBinding
import com.dr.udaan.retrofit.AllRequest.RegisterRequest
import com.dr.udaan.retrofit.AllRequest.ResendOtpRequest
import com.dr.udaan.retrofit.AllRequest.VerifyOtpRequest
import com.dr.udaan.retrofit.Retrofitinstance
import com.dr.udaan.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import java.util.concurrent.TimeUnit

class OtpLogin : BaseFragment<FragmentOtpLoginBinding>() {

    private lateinit var phone: String
    private lateinit var password: String
    private lateinit var verificationId: String

    private lateinit var auth: FirebaseAuth

    private var resendTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentOtpLoginBinding.inflate(layoutInflater)

        phone = arguments?.getString("phone").toString()
        password = arguments?.getString("password").toString()
        verificationId = arguments?.getString("verificationId").toString()

        auth = Firebase.auth

        action()

        startResendTimer()

        return binding.root
    }

    private fun action() {

        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }

        binding.signup.setOnClickListener {
            if (binding.otp.text.toString().isEmpty() || binding.otp.text.toString().length < 6) {
                Snackbar.make(binding.root, "Incorrect OTP", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            showLoading()
            verifyPhoneNumberWithCode(verificationId, binding.otp.text.toString())
        }

        binding.resend.setOnClickListener {
            binding.resend.isEnabled = false
            showLoading()
            resendVerificationCode(phone, resendToken)
        }

        binding.login.setOnClickListener(){
            findNavController().navigate(R.id.login)
        }

    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity as AppCompatActivity)
            .setCallbacks(resendCallBack)
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        auth.signInWithCredential(credential).addOnCompleteListener {
            dismissLoading()
            if (it.isSuccessful) {
                // Success
                showLoading()
                CoroutineScope(IO)
                    .launch {
                        register(phone, password)
                    }
            } else {
                Toast.makeText(mContext, "Verification failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private suspend fun register(mobileNo: String, password: String) {

        val request = RegisterRequest(
            "1", mobileNo, password, password
        )

        try {

            val response = Retrofitinstance.getRetrofit().register(request).await()

            dismissLoading()

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
            dismissLoading()
            e.printStackTrace()
            Toast.makeText(mContext, "${e.message}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startResendTimer() {

        var timeRemain = 60

        resendTimer = object: CountDownTimer(60000, 1000) {
            override fun onTick(p0: Long) {
                timeRemain--
                binding.resend.text = "Resend OTP: 00:${String.format("2d", timeRemain)}"
            }

            override fun onFinish() {
                binding.resend.text = "Resend"
                binding.resend.isEnabled = true
            }

        }.start()

    }

    private val resendCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            dismissLoading()
        }

        override fun onVerificationFailed(e: FirebaseException) {
            dismissLoading()
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            dismissLoading()
            binding.resend.text = "Code Sent"
            Handler(Looper.getMainLooper())
                .postDelayed({
                    startResendTimer()
                }, 1000)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun getViewBinding() = FragmentOtpLoginBinding.inflate(layoutInflater)

}
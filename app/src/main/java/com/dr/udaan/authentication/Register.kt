package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentOtpLoginBinding
import com.dr.udaan.databinding.FragmentRegisterBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class Register : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mVerificationId: String
    lateinit var  mResendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var mContext: Context
    lateinit var getotp: FragmentOtpLoginBinding
    var number : String = binding.phone.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    private fun callBacks() {

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }
            override fun onVerificationFailed(e: FirebaseException) {

            }
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            )
            {
                mVerificationId = verificationId
                mResendToken = token

            }
        }
    }

//  private fun verifyOtp() {
//      getotp.signup.setOnClickListener() {
//          verifyPhoneNumberWithCode(
//              verifyPhoneNumberWithCode(
//                  mVerificationId,
//                  getotp.otp.text.toString()
//              )
//          )
//      }
//  }

    private fun sendOTP(number: String){
        callBacks()
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber("+91" + binding.phone.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity as AppCompatActivity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

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
            if (binding.password.text.toString().trim().isEmpty()) {
                binding.password.error = "Enter your passwords here"
                return@setOnClickListener
            }

            if (number.isNotEmpty()){
                number = "+91$number"
                sendOTP(number)
            }
          //  val mobileNO = binding.phone.text.toString().trim()
        //    val password = binding.passwords.text.toString().trim()

          //  CoroutineScope(IO)
            //    .launch {

              //  }
//            Navigation.findNavController(binding.root).navigate(R.id.otpLogin)
        }

        binding.login.setOnClickListener() {
            findNavController().navigate(R.id.login)
        }
    }


    private fun verifyPhoneNumberWithCode(verificationId: String, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        val user = Firebase.auth.currentUser

        if (user != null) {

        } else {

        }

        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val bundle = Bundle()
                    bundle.putString("phone_number", binding.phone.text.toString())
                    bundle.putString("password",binding.password.text.toString())
                    findNavController().navigate(R.id.otpLogin, bundle)
                }

                else {
                    Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}


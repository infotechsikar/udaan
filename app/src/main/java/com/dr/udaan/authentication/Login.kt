package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentLoginBinding
import com.dr.udaan.retrofit.AllRequest.LoginRequest
import com.dr.udaan.retrofit.Retrofitinstance.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class Login : Fragment() {
 lateinit var binding: FragmentLoginBinding
 lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

  private suspend fun login (mobileNo: String, password: String){
      val request = LoginRequest(mobileNo,password)

      val response = getRetrofit().login(request).await()

      if (response.success == false){
          withContext(Main){
              Toast.makeText(mContext, "Please enter correct information", Toast.LENGTH_SHORT).show()
          }
      }
      else {

      }
  }

    private fun action() {
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
        
        binding.login.setOnClickListener {
            if (binding.phone.text.toString().trim().isEmpty()){
                binding.phone.error = "Enter your phone number"
                return@setOnClickListener
            }

            if (binding.passwords.text.toString().trim().isEmpty()){
                binding.passwords.error = "Enter your passwords here"
                return@setOnClickListener
            }

            findNavController().navigate(R.id.home)
            val mobileNo = binding.phone.text.toString()
            val password = binding.passwords.text.toString()

            CoroutineScope(IO).launch {
               // login(mobileNo,password)
               // findNavController().navigate(R.id.home)
            }
        }

        binding.forgotPassword.setOnClickListener() {
            findNavController().navigate(R.id.forgotPassword)
        }

        binding.signup.setOnClickListener(){
            findNavController().navigate(R.id.register)
        }
    }

       override fun onAttach(context: Context) {
          super.onAttach(context)
          mContext = context
    }

}
package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentLoginBinding
import com.dr.udaan.api.retrofit.AllRequest.LoginRequest
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.api.retrofit.Retrofitinstance.getRetrofit
import com.dr.udaan.room.MyDatabase
import com.dr.udaan.room.UserData
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

class Login : BaseFragment<FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        action()
        super.onViewCreated(view, savedInstanceState)
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
                                MyDatabase.getDatabase(mContext).userData().insert(response.userData!!)
                                setUserData(response.userData!!)
                             findNavController().navigate(R.id.home)
                            } catch (e: Exception){
                                e.printStackTrace()
                            }
                        } else {
                            Toast.makeText(mContext, response.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    dismissLoading()
                    withContext(Dispatchers.Main){


                        Toast.makeText(mContext, e.message.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("LOgin", "action: ${e.message}")
                    }
                }
            }
        }

        binding.forgotPassword.setOnClickListener() {
            findNavController().navigate(R.id.forgotPassword)
        }

        binding.signup.setOnClickListener(){
            findNavController().navigate(R.id.register)
        }
    }

    private fun setUserData(userData: UserData) {

        Toast.makeText(mContext, userData.name.toString(), Toast.LENGTH_SHORT).show()
        AppFunctions.setUserName(mContext,userData.name.toString())
        AppFunctions.setPhone(mContext,userData.mobileNo.toString())
        AppFunctions.setEmail(mContext,userData.email.toString())
        AppFunctions.setUserId(mContext,userData.id!!)


    }

    override fun onAttach(context: Context) {
          super.onAttach(context)
          mContext = context
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

}
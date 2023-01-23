package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.databinding.FragmentResetPasswordBinding
import com.dr.udaan.util.AppFunctions
import kotlinx.coroutines.*
import retrofit2.await
import retrofit2.awaitResponse

class ResetPassword : Fragment() {
    lateinit var binding: FragmentResetPasswordBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        action()

        return binding.root
    }

    private fun action() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.resetPassword.setOnClickListener{
            if (binding.currentPassword.text.toString().trim().isEmpty()){
                binding.currentPassword.error = "Enter your current password"
                return@setOnClickListener
            }
            if (binding.newPassword.text.toString() != binding.confirmPassword.text.toString()){
                binding.confirmPassword.error = "Please Enter same password"
                return@setOnClickListener
            }
            if ( binding.newPassword.text.toString().trim().length < 6){
                Toast.makeText(mContext, "password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                binding.newPassword.error = "password must be at least 6 characters"
                return@setOnClickListener
            }
//            if (binding.confirmPassword.text.toString().trim().isEmpty()){
//                binding.confirmPassword.error = "Enter your confirm password"
//                return@setOnClickListener
//            }
            CoroutineScope(Dispatchers.IO)
                .launch {
                    changePassword()
                }
            Toast.makeText(mContext, "reset password done", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun changePassword() {
        val call  = Retrofitinstance.getRetrofit().changePassword(
            AppFunctions.getUserId(),binding.currentPassword.text.toString(),binding.newPassword.text.toString(),
            binding.confirmPassword.text.toString())
        val response = call.awaitResponse()

        if (response.code()==200){
            withContext(Dispatchers.Main){
                if (response.body()!!.success == false) {
                    Toast.makeText(mContext, response.body()!!.message.toString(), Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(mContext, response.body()!!.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        else {
            Toast.makeText(mContext, "something went wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
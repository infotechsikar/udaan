package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentResetPasswordBinding

class ResetPassword : Fragment() {
    lateinit var binding: FragmentResetPasswordBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    private fun action() {

        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }

        binding.resetPassword.setOnClickListener{
            if (binding.phone.text.toString().trim().isEmpty()){
                binding.phone.error = "Enter your phone number or email address"
                return@setOnClickListener
            }
            Navigation.findNavController(binding.root).navigate(R.id.login)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
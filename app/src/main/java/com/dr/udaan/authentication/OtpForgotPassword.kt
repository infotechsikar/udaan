package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentOtpForgotPasswordBinding

class OtpForgotPassword : Fragment() {
   lateinit var binding: FragmentOtpForgotPasswordBinding
   lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentOtpForgotPasswordBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    private fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }

        binding.verify.setOnClickListener{
            findNavController().navigate(R.id.resetPassword)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
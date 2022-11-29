package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentRegisterBinding

class Register : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    private fun action() {
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.continues.setOnClickListener{
            if (binding.phone.text.toString().trim().isEmpty() ||
                binding.phone.text.toString().trim().length != 10){
                binding.phone.error = "Enter your phone number"
                return@setOnClickListener
            }
            Navigation.findNavController(binding.root).navigate(R.id.otpLogin)
        }

        binding.login.setOnClickListener(){
            findNavController().navigate(R.id.login)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}


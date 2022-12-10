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
import com.dr.udaan.databinding.FragmentLoginBinding

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
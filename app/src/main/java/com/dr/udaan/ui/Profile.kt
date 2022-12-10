package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentProfile2Binding

class Profile : Fragment() {
  lateinit var binding: FragmentProfile2Binding
  lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentProfile2Binding.inflate(layoutInflater)
        action()
        return binding.root
    }

    fun action(){
        binding.cl.setOnClickListener(){
            findNavController().navigate(R.id.information)
        }
        binding.cl2.setOnClickListener(){
            findNavController().navigate(R.id.resetPassword)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
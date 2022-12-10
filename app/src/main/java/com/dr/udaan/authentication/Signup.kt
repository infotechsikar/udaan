package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentSignupBinding

class Signup : Fragment() {
    lateinit var binding: FragmentSignupBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
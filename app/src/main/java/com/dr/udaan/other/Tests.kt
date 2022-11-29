package com.dr.udaan.other

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.databinding.FragmentTestsBinding

class Tests : Fragment() {
  lateinit var binding: FragmentTestsBinding
  lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentTestsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }



}
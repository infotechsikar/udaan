package com.dr.udaan.nevigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.databinding.FragmentContactUsBinding
import com.dr.udaan.ui.BaseFragment

 class ContactUs : BaseFragment<FragmentContactUsBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentContactUsBinding.inflate(layoutInflater)
        return binding.root
    }

     override fun onAttach(context: Context) {
         super.onAttach(context)
         mContext = context
     }

     override fun getViewBinding() = FragmentContactUsBinding.inflate(layoutInflater)
}
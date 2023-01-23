package com.dr.udaan.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.R
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentGrowthBinding

class Growth : BaseFragment<FragmentGrowthBinding>(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_growth, container, false)
    }
    override fun getViewBinding() = FragmentGrowthBinding.inflate(layoutInflater)
}
package com.dr.udaan.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentLibraryBinding
import com.dr.udaan.util.Const


class Library : BaseFragment<FragmentLibraryBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action()
    }

    fun action() {
        binding.savedBlogs.setOnClickListener(){
            findNavController().navigate(R.id.allBlogs, Bundle().apply { putBoolean(Const.IS_FROM_SAVED, true) })
        }
        binding.savedQuestions.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.test)
        }
        binding.savedTests.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.test)
        }
    }

    override fun getViewBinding() = FragmentLibraryBinding.inflate(layoutInflater)

}
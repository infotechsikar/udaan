package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentLibraryBinding


class Library : Fragment() {
    lateinit var binding: FragmentLibraryBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLibraryBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    fun action(){
        binding.cl.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.test)
        }
        binding.cl2.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.test)
        }
        binding.cl3.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.test)
        }
        binding.cl4.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.test)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
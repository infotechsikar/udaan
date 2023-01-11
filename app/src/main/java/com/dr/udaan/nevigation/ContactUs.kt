package com.dr.udaan.nevigation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dr.udaan.databinding.FragmentContactUsBinding

class ContactUs : Fragment() {
    private lateinit var binding: FragmentContactUsBinding
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactUsBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    private fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
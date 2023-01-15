package com.dr.udaan.ui.fragments.privacyandabout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentContactUsBinding
import com.dr.udaan.base.BaseFragment

class ContactUs: BaseFragment<FragmentContactUsBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actions()
    }

     fun actions() {
         binding.toolbar.setNavigationOnClickListener {
             findNavController().popBackStack()
         }
     }

     override fun getViewBinding() = FragmentContactUsBinding.inflate(layoutInflater)
}
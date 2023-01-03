package com.dr.udaan.other

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentScoreBoardBinding
import com.dr.udaan.ui.BaseFragment

class ScoreBoard : BaseFragment<FragmentScoreBoardBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewBinding() =  FragmentScoreBoardBinding.inflate(layoutInflater)

}
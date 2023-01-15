package com.dr.udaan.ui.fragments.tests

import android.os.Bundle
import android.view.View
import com.dr.udaan.databinding.FragmentScoreBoardBinding
import com.dr.udaan.base.BaseFragment

class ScoreBoard : BaseFragment<FragmentScoreBoardBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewBinding() =  FragmentScoreBoardBinding.inflate(layoutInflater)

}
package com.dr.udaan.other.tests.savedtest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentSavedBinding
import com.dr.udaan.databinding.FragmentTestBinding
import com.dr.udaan.databinding.FragmentTestSetsBinding


class SavedTests : Fragment() {
    private lateinit var binding: FragmentTestSetsBinding
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentTestSetsBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_test_sets, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}

/**
 * Room Database --> Saved Items
 * Scoreboard --> Functionality - APIx
 * Questions --> Bug Fix
 * Submit Answers --> API Calling
 * Privacy Policy --> APIx
 * Notes --> Need to Impl --> Have code
 */
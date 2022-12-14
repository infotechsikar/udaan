package com.dr.udaan.other

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dr.udaan.databinding.FragmentTestBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Test : Fragment() {
    lateinit var binding: FragmentTestBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentTestBinding.inflate(layoutInflater)
        setTabLayout()
        return binding.root
    }

    private fun setTabLayout() {
        val tabs = arrayOf("Tests", "Tests Set")
        val adapterHomePage = TestPageViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.vp.adapter = adapterHomePage
        TabLayoutMediator(
            binding.tablayout, binding.vp
        )
        { tab: TabLayout.Tab, position: Int ->
            tab.text = tabs[position]
        }.attach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
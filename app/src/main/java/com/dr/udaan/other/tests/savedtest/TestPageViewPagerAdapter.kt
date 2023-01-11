package com.dr.udaan.other.tests.savedtest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dr.udaan.other.tests.Tests

class TestPageViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Tests()
            1 -> fragment = SavedTests()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return 2
    }

}
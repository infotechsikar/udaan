package com.dr.udaan.ui.fragments.saved

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dr.udaan.ui.fragments.tests.Tests

class TestPageViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Tests()
            1 -> fragment = TestSets()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return 2
    }

}
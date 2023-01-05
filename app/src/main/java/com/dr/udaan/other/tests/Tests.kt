package com.dr.udaan.other.tests

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dr.udaan.MyApp.Companion.myDatabase
import com.dr.udaan.api.TestsAPI
import com.dr.udaan.databinding.FragmentTestsBinding
import com.dr.udaan.api.retrofit.Pojo.TestData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.ui.BaseFragment
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class Tests : BaseFragment<FragmentTestsBinding>() {

    private var categoryId = -1
    private var pageNo = 1

    init {
        categoryId = arguments?.getInt(Const.CATEGORY_ID) ?: -1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(IO)
            .launch {
                fetchTests()
                initTests()
            }

    }

    private suspend fun fetchTests() {
        withContext(Main) {
            showLoading()
        }
        TestsAPI.fetchTests(categoryId, pageNo)
        withContext(Main) {
            dismissLoading()
        }
    }

    private suspend fun initTests() {

        val adapterTests = AdapterTests(findNavController())

        withContext(Main) {
            binding.rv.adapter = adapterTests
        }

        myDatabase?.tests()?.getTestsLive()?.observe(viewLifecycleOwner) {
            CoroutineScope(Main).launch {
                adapterTests.updateData(it)
                dismissLoading()
            }
        }

    }

    override fun getViewBinding() = FragmentTestsBinding.inflate(layoutInflater)

}
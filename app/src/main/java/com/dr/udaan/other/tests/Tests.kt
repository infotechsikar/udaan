package com.dr.udaan.other.tests

import android.os.Bundle
import android.util.Log
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryId = arguments?.getInt(Const.EXAM_ID) ?: -1

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
        val list = TestsAPI.fetchTests(categoryId, pageNo)
        withContext(Main) {
            dismissLoading()
        }

        val adapterTests = AdapterTests(findNavController())

        withContext(Main) {
            binding.rv.adapter = adapterTests
            adapterTests.updateData(list ?: emptyList())
        }
    }

    private suspend fun initTests() {

        myDatabase?.tests()?.getTestsLive()?.observe(viewLifecycleOwner) {
            Log.d("TAG", "initTests: ${it.size}")
            CoroutineScope(Main).launch {

                dismissLoading()
            }
        }

    }

    override fun getViewBinding() = FragmentTestsBinding.inflate(layoutInflater)

}
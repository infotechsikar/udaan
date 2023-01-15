package com.dr.udaan.ui.fragments.tests

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dr.udaan.api.InTheBackground
import com.dr.udaan.api.retrofit.Pojo.TestData
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentTestsBinding
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Tests : BaseFragment<FragmentTestsBinding>() {

    private var _forSearch = false
    private var categoryId = -1
    private var pageNo = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _forSearch = arguments?.getBoolean(Const.IS_SEARCH, false) ?: false
        categoryId = arguments?.getInt(Const.EXAM_ID) ?: -1

        actions()

        CoroutineScope(IO)
            .launch {
                if (!_forSearch) {
                    fetchTests()
                } else {
                    /**
                     * Force open the keyboard when the page is opened for search tests
                     */
                    withContext(Main) {
                        binding.search.requestFocus()
                        AppFunctions.openKeyboard(activity as AppCompatActivity)
                    }
                }
                withContext(Main) {
                    updateUi()
                }
            }

    }

    private fun updateUi() {
        if (_forSearch) {
            binding.hintText.isVisible = true
            binding.hintText.text = "Type something to search"
        }
    }

    private fun actions() {

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                CoroutineScope(IO).launch {
                    search(v.text.toString())
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private suspend fun search(text: String) {
        withContext(Main) {
            binding.hintText.isVisible = false
            AppFunctions.closeKeyboard(activity as AppCompatActivity, binding.root)
           binding.searchAnim.isVisible = true
        }
        if (text.isNotEmpty()) {
            val list = InTheBackground.searchTests(text)
            setRv(list)
        }
    }

    private suspend fun fetchTests() {
        withContext(Main) {
            showLoading()
        }
        val list = InTheBackground.fetchTests(categoryId, pageNo)
        setRv(list)
    }

    private suspend fun setRv(list: List<TestData>?) {

        withContext(Main) {
            dismissLoading()
            binding.searchAnim.isVisible = false
        }

        val adapterTests = AdapterTests(findNavController())

        withContext(Main) {
            binding.rv.adapter = adapterTests
            adapterTests.updateData(list ?: emptyList())
        }
        binding.hintText.isVisible = list?.isEmpty() ?: false
        binding.hintText.text = "No result found"
    }

    override fun getViewBinding() = FragmentTestsBinding.inflate(layoutInflater)

}
package com.dr.udaan.ui.fragments.blog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dr.udaan.MyApp
import com.dr.udaan.api.retrofit.Pojo.BlogData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentAllBlogsBinding
import com.dr.udaan.ui.homepage.ShimmerAdapterBlogs
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class AllBlogs : BaseFragment<FragmentAllBlogsBinding>() {

    private var _fromSaved = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _fromSaved = arguments?.getBoolean(Const.IS_FROM_SAVED, false) ?: false

        actions()
        CoroutineScope(IO).launch {
            if (_fromSaved) {
                withContext(Main) {
                    getSavedBlogs()
                }
            } else {
                getBlogs()
            }
        }

    }

    private suspend fun getBlogs() {

        withContext(Main) {
            binding.rv.adapter = ShimmerAdapterBlogs(10)
        }

        var bList: ArrayList<BlogData>
        val response = Retrofitinstance.getRetrofit().blogs().awaitResponse()
        withContext(Main) {
            if (response.isSuccessful) {
                bList = response.body()?.blogdata ?: ArrayList()
                binding.rv.adapter = AdapterBlog(bList, findNavController())
                binding.emptyGhost.isVisible = bList.isEmpty()
            } else {
                Toast.makeText(
                    mContext,
                    "failed to load blog! please try after some time!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.emptyGhost.isVisible = true
            }
        }
    }

    private fun getSavedBlogs() {
        binding.emptyGhost.isVisible =
            MyApp.myDatabase?.blogData()?.getSavedBlogs()?.isEmpty() == true
        MyApp.myDatabase?.blogData()?.getSavedBlogsLive()?.observe(viewLifecycleOwner) {
            binding.emptyGhost.isVisible = it.isEmpty()
            binding.rv.adapter = AdapterBlog(ArrayList(it), findNavController())
        }
    }

    private fun actions() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun getViewBinding() = FragmentAllBlogsBinding.inflate(layoutInflater)

}
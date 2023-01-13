package com.dr.udaan.ui.homepage

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.adapter.AdapterSliderHome
import com.dr.udaan.api.retrofit.Pojo.Blogdata
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.api.retrofit.Pojo.CategoryData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.ui.BaseFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.await
import retrofit2.awaitResponse
import java.lang.Runnable

class Home : BaseFragment<FragmentHomeBinding>() {

    var handler = Handler(Looper.getMainLooper())
    lateinit var runnable: Runnable
    var adapter: AdapterSliderHome? = null
    private var sliderImages = arrayListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        action()

        CoroutineScope(IO)
            .launch {

                getSlider()

                withContext(Main) {
                    binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    adapter = AdapterSliderHome(binding.vp, sliderImages)
                    binding.vp.adapter = adapter

                    runnable = kotlinx.coroutines.Runnable {
                        binding.vp.currentItem = binding.vp.currentItem + 1
                        handler.postDelayed(runnable, 3000)
                    }

                    handler.postDelayed(runnable, 3000)
                }
                getCategories()
                getBlogs()
            }

    }

    override fun onDestroy() {
        if (this::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }


    fun action() {
        binding.viewAll.setOnClickListener() {
            findNavController().navigate(R.id.exam)
        }
    }


    private suspend fun getSlider() {

        try {

            val response = Retrofitinstance.getRetrofit().sliders().await().categoryData

            for (data in response) {
                sliderImages.add(data.sliderImage!!)
                adapter?.notifyItemInserted(sliderImages.lastIndex)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private suspend fun getCategories() {

        try {

            val categoryData = APIData.fetchCategories()
            val cList = ArrayList<CategoryData>()

            for (i in 0..3) {
                if (categoryData.size > i) {
                    cList.add(categoryData[i])
                }
            }

            withContext(Main) {
                binding.cl2.adapter = AdapterExams(cList, findNavController())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private suspend fun getBlogs() {

        var bList: ArrayList<Blogdata>
        val response = Retrofitinstance.getRetrofit().blogs().awaitResponse()
        withContext(Main) {
            if (response.isSuccessful) {

                bList = response.body()?.blogdata ?: ArrayList()

                binding.rv.adapter = AdapterBlog(bList, findNavController())
            } else {
                Toast.makeText(
                    mContext,
                    "failed to load blog! please try after some time!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}
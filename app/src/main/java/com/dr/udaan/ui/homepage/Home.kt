package com.dr.udaan.ui.homepage

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.adapter.AdapterSliderHome
import com.dr.udaan.api.retrofit.Pojo.BlogData
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.api.retrofit.Pojo.CategoryData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.ui.activities.MainActivity
import com.dr.udaan.ui.fragments.blog.AdapterBlog
import com.dr.udaan.util.Const
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
                withContext(Main) {
                    shimmerEffects()
                }
                getSlider()
                withContext(Main) {
                    setSlider()
                }
                getCategories()
                getBlogs()
            }

    }

    private fun setSlider() {
        binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        adapter = AdapterSliderHome(binding.vp, sliderImages)
        binding.vp.adapter = adapter

        runnable = Runnable {
            binding.vp.currentItem = binding.vp.currentItem + 1
            handler.postDelayed(runnable, 3000)
        }

        handler.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        if (this::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }

    fun action() {
        binding.viewAll.setOnClickListener() {
            MainActivity.bottomNavigationView?.selectedItemId = R.id.exam
        }
        binding.notes.setOnClickListener {
            findNavController().navigate(R.id.notes)
        }
        binding.save.setOnClickListener {
            MainActivity.bottomNavigationView?.selectedItemId = R.id.library
        }
        binding.viewAllBlog.setOnClickListener {
            findNavController().navigate(R.id.allBlogs)
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

    private fun shimmerEffects() {
        binding.rvExams.adapter = ShimmerAdapterExams(4)
        binding.rv.adapter = ShimmerAdapterBlogs(3)
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
                binding.rvExams.adapter = AdapterExams(categoryData) {
                    val args = Bundle()
                    args.putInt(Const.EXAM_ID, it)
                    findNavController().navigate(R.id.tests,args)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private suspend fun getBlogs() {

        try {
            var bList: ArrayList<BlogData>
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}
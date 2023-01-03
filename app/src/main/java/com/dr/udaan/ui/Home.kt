package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.adapter.AdapterSliderHome
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.databinding.RowItemBlogBinding
import com.dr.udaan.databinding.RowItemExamBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.retrofit.Pojo.Blogdata
import com.dr.udaan.retrofit.Pojo.CategoryData
import com.dr.udaan.retrofit.Pojo.SliderData
import com.dr.udaan.retrofit.Retrofitinstance
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.await
import java.lang.Runnable

class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var mContext: Context
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private val list = ArrayList<ModelSlider>()
    private var sliderImages = arrayListOf<String>()
    private var listBlog = ArrayList<Blogdata>()

    override fun onDestroy() {
        if (this::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

          action()
        CoroutineScope(IO)
            .launch {
//            getSlider()
//            withContext(Dispatchers.Main) {
//                binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//                val adapter = AdapterSliderHome(binding.vp, sliderImages)
//                binding.vp.adapter = adapter
//
//                runnable = kotlinx.coroutines.Runnable {
//                    binding.vp.currentItem = binding.vp.currentItem + 1
//                    handler.postDelayed(runnable, 3000)
//                }
//                handler.postDelayed(runnable, 3000)
//            }
                getCategories()
                blogs()

            }
        return binding.root
    }

    fun action() {

        binding.viewAll.setOnClickListener() {
            findNavController().navigate(R.id.exam)
        }
        binding.viewAllBlog.setOnClickListener(){
            findNavController().navigate(R.id.blogs)
        }
        binding.notes.setOnClickListener(){
            findNavController().navigate(R.id.notes)
        }
        binding.save.setOnClickListener(){
            findNavController().navigate(R.id.saved)
        }
        binding.score.setOnClickListener(){
            findNavController().navigate(R.id.scoreBoard)
        }
        binding.growth.setOnClickListener(){
            findNavController().navigate(R.id.growth)
        }
    }

    private fun slider() {
        binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
//        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
//        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
//        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))

     //   val adapterCurrentNews = AdapterSlider(binding.vp, list)

    //    binding.vp.adapter = adapterCurrentNews
        runnable = kotlinx.coroutines.Runnable {
            binding.vp.currentItem = binding.vp.currentItem + 1
            handler.postDelayed(runnable, 3000)
        }

        handler.postDelayed(runnable, 3000)

//        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                handler.removeCallbacks(runnable)
//                handler.postDelayed(runnable, 2000)
//            }
//        }
//        )
    }

    private suspend fun getSlider(){
        var sliderData = ArrayList<SliderData>()
     //   val response = Retrofitinstance.getRetrofit().sliders().await().categoryData
//        Log.d("SINFOO",response.toString())
     //   for (data in response) {
//            Log.d("-->", data.id!!.toString())
        //    sliderImages.add(data.sliderImage!!)
     // }
      //  sliderData = response.categoryData
    }

    private fun getCategories() {
        CoroutineScope(IO)
            .launch {
                val categoryData = APIData.fetchCategories()
                val cList = ArrayList<CategoryData>()

                for (i in 0..3) {
                    if (categoryData.size > i) {
                        cList.add(categoryData[i])
                    }
                }
                withContext(Main){
                    binding.cl2.adapter = AdapterExams(cList,findNavController())
                }
            }
    }

    private suspend fun blogs(){
        val response = Retrofitinstance.getRetrofit().blogs().await()

        listBlog = response.blogdata

        withContext(Main){
          binding.rv.adapter = AdapterBlog(findNavController(),listBlog)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterBlog( private val navController: NavController, private val list: ArrayList<Blogdata>) :
        RecyclerView.Adapter<AdapterBlog.PlaceHolder>() {
        inner class PlaceHolder(itemView: View,val dBinding: RowItemBlogBinding) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding =
                RowItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlaceHolder(binding.root,binding)
        }

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {

             holder.dBinding.title.text = list[position].title

            holder.itemView.setOnClickListener {
                val blogId = list[position].id
                val args = Bundle()
                if (blogId != null) {
                    args.putParcelable("blog",list[position])

                }
                navController.navigate(R.id.blogs,args)
            }

        }

        override fun getItemCount(): Int {
            return list.size
        }

        }
}
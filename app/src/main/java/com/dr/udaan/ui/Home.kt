package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.databinding.RowItemBlogBinding

class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var mContext: Context
    var handler = Handler(Looper.getMainLooper())
    lateinit var runnable :  Runnable
    private val list = ArrayList<ModelSlider>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.rv.adapter = AdapterSearchPlaces()
        slider()
        return binding.root
    }

    private fun slider() {

        binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=ce278e1e-7375-4bd6-9dfb-9bbe0f432db5"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=ce278e1e-7375-4bd6-9dfb-9bbe0f432db5"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=ce278e1e-7375-4bd6-9dfb-9bbe0f432db5"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=ce278e1e-7375-4bd6-9dfb-9bbe0f432db5"))

        val adapterCurrentNews = AdapterSlider(binding.vp,list)

        binding.vp.adapter = adapterCurrentNews
        runnable = kotlinx.coroutines.Runnable {
            binding.vp.currentItem = binding.vp.currentItem + 1
            handler.postDelayed(runnable, 2000)
        }

        handler.postDelayed(runnable, 2000)

//        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                handler.removeCallbacks(runnable)
//                handler.postDelayed(runnable, 2000)
//            }
//        }
//        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterSearchPlaces() : RecyclerView.Adapter<AdapterSearchPlaces.PlaceHolder>() {
        inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding =
                RowItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlaceHolder(binding.root)
        }

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 10
        }

    }

}
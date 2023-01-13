package com.dr.udaan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.bumptech.glide.Glide
import com.dr.udaan.R
import com.dr.udaan.databinding.RowItemVpBinding
import com.dr.udaan.api.retrofit.Pojo.SliderData

class AdapterSliderHome(private val viewPager2: ViewPager2, private val list: ArrayList<String>) :
    RecyclerView.Adapter<AdapterSliderHome.SliderHolder>() {

    inner class SliderHolder(itemView: View, val dBinding: RowItemVpBinding) :
        RecyclerView.ViewHolder(itemView) {

    }

    private val runnable = Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderHolder {
        val binding = RowItemVpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: SliderHolder, position: Int) {

//        Glide.with(holder.itemView.context)
//            .load(list[position])
//            .into(holder.dBinding.image)

        holder.dBinding.image.load(R.drawable.imageview)

        if (position == list.lastIndex - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
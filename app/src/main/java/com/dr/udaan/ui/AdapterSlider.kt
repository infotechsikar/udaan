package com.dr.udaan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dr.udaan.R

class AdapterSlider(private val viewPager2: ViewPager2, var list: ArrayList<ModelSlider>) :
    RecyclerView.Adapter<AdapterSlider.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_vp, parent, false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position].image)
        if (position == list.lastIndex - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        fun setData(image: String?) {
            Glide.with(itemView.context)
                .load(image)
                .into(this.image)
        }

        init {
            image = itemView.findViewById(R.id.image)
        }
    }

    private val runnable = Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }
}
//handler = android.os.Handler()
//runnable = object : Runnable {
//    override fun run() {
//        if (binding.vp.getCurrentItem() >= 4) {
//            binding.vp.setCurrentItem(0, true)
//        } else {
//            binding.vp.setCurrentItem(binding.vp.getCurrentItem() + 1, true)
//        }
//        handler.postDelayed(runnable, 3000)
//    }
//}
//handler.postDelayed(runnable, 3000)
//}
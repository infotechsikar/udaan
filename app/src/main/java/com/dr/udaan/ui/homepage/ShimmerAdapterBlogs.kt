package com.dr.udaan.ui.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.databinding.ShimmerItemBlogsBinding

class ShimmerAdapterBlogs(
    private val items: Int = 3
): RecyclerView.Adapter<ShimmerAdapterBlogs.BlogHolder>() {

    class BlogHolder(itemView: View, val dBinding: ShimmerItemBlogsBinding) :
        RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val binding =
            ShimmerItemBlogsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.dBinding.shimmerView.startShimmer()
    }

    override fun getItemCount() = items

}

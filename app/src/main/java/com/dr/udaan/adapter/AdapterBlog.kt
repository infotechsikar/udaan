package com.dr.udaan.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.api.retrofit.Pojo.Blogdata
import com.dr.udaan.databinding.RowItemBlogBinding
import com.dr.udaan.util.Const

class AdapterBlog(private val blist: ArrayList<Blogdata>, private val navController: NavController) :RecyclerView.Adapter<AdapterBlog.BlogHolder>() {

    class BlogHolder(itemView: View, val dBinding: RowItemBlogBinding) :
        RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val binding =
            RowItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {

        holder.dBinding.title.text = blist[position].title

        holder.itemView.setOnClickListener() {
            val blogId = blist[position].id
            val bundle = Bundle()
            if (blogId != null) {
                bundle.putInt(Const.EXAM_ID, blogId)
                bundle.putParcelable("blog",blist[position])
            }
            navController.navigate(R.id.blogs, bundle)
        }
    }

    override fun getItemCount(): Int {
        return blist.size
    }

}
package com.dr.udaan.ui.fragments.blog

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.api.retrofit.Pojo.BlogData
import com.dr.udaan.databinding.RowItemBlogBinding
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AdapterBlog(
    private val blist: ArrayList<BlogData>,
    private val navController: NavController
) :
    RecyclerView.Adapter<AdapterBlog.BlogHolder>() {

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

        var isSaved = MyApp.myDatabase?.blogData()
            ?.getBlogById(blist[position].id) != null

        Log.d("TAG", "onBindViewHolder: $isSaved ${MyApp.myDatabase?.blogData()
            ?.getBlogById(blist[position].id)?.title}")

        holder.dBinding.share.setOnClickListener {

        }

        holder.dBinding.save.setOnClickListener {
            if (!isSaved) {
                blist[position].is_saved = true
                MyApp.myDatabase?.blogData()?.insert(blist[position])
            } else {
                blist[position].is_saved = false
                MyApp.myDatabase?.blogData()
                    ?.delete(blist[position].id)
            }
            isSaved = !isSaved
            notifyItemChanged(position)
        }

        if (isSaved) {
            holder.dBinding.save.iconTint = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.blue))
            holder.dBinding.save.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.blue)))
            holder.dBinding.save.text = "Saved"
        } else {
            holder.dBinding.save.iconTint = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.black))
            holder.dBinding.save.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.black)))
            holder.dBinding.save.text = "Save"
        }

        holder.itemView.setOnClickListener() {
            val blogId = blist[position].id
            val bundle = Bundle()
            bundle.putString(Const.BLOG_ID, blogId.toString())
            bundle.putString(Const.TITLE, blist[position].title)
            bundle.putString(Const.CONTENT, blist[position].description)
            bundle.putString(Const.DATE_TIME, blist[position].createdAt)
            navController.navigate(R.id.blogs, bundle)
        }
    }

    override fun getItemCount(): Int {
        return blist.size
    }

}

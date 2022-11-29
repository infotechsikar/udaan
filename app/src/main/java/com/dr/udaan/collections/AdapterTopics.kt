package com.dr.udaan.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R

class AdapterTopics(private val list:ArrayList<ModelTopics>): RecyclerView.Adapter<AdapterTopics.TopicHolder>() {

   inner class TopicHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

      var text:TextView

     fun setData(text:String?){
         this.text.text = text
     }

     init {
         text = itemView.findViewById(R.id.courses_name)
     }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_contents, parent, false)
        return TopicHolder(view)
    }

    override fun onBindViewHolder(holder: TopicHolder, position: Int) {
        holder.setData(list[position].title)
    }

    override fun getItemCount() = run{
    list.size
    }

}
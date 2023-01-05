package com.dr.udaan.other.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.databinding.RowItemExamsBinding
import com.dr.udaan.other.Test
import com.dr.udaan.api.retrofit.Pojo.TestData
import com.dr.udaan.util.Const

class AdapterTests( private val navController: NavController): RecyclerView.Adapter<AdapterTests.TestHolder>() {

    private val dataList = HashSet<TestData>()

    inner class TestHolder(itemView: View, val dBinding: RowItemExamsBinding) : RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        val binding = RowItemExamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {

        val list = ArrayList(dataList)

        holder.dBinding.test.text = list[position].categoryId.toString()

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            list[position].id?.let { it1 -> bundle.putInt(Const.TEST_ID, it1) }
            navController.navigate(R.id.questionFragment,bundle)
        }

    }

    override fun getItemCount() = dataList.size

    fun updateData(data: List<TestData>) {

        // add data
        data.map {
            dataList.add(it)
        }

        dataList.map { i->
           if (!data.any{it == i}) {
               dataList.remove(i)
           }
        }

    }

}
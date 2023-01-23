package com.dr.udaan.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.api.retrofit.Pojo.Transactions
import com.dr.udaan.databinding.RowItemTransactionBinding

class AdapterTransactions(private val list: List<Transactions>, private val navController: NavController):RecyclerView.Adapter<AdapterTransactions.TransactionHolder>(){

   inner class TransactionHolder(itemView: View, val dBinding: RowItemTransactionBinding)
       : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val binding = RowItemTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  TransactionHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
           holder.dBinding.status.text = if(list[position].status.toString().isEmpty()) "Pending" else list[position].status.toString()
           holder.dBinding.price.text = list[position].amount.toString()
           holder.dBinding.date.text = list[position].createdAt.toString().substring(0,10)
           holder.dBinding.txnId.text = list[position].transactionId.toString()

        if (list[position].status != "Completed")
            holder.dBinding.status.setTextColor(Color.parseColor("#f7e648"))

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("transactions",list[position])
            navController.navigate(R.id.paymentStatus,bundle)
        }
    }

    override fun getItemCount(): Int {
      return  list.size
    }

}


//holder.itemView.setOnClickListener {
//    val args = Bundle()
//    args.putParcelable("transactions",list[position])
//    navController.navigate(R.id.action_recentTransactions_to_paymentStatus,args)
//}
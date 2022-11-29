package com.dr.udaan.collections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dr.udaan.databinding.ActivityTopicsBinding
import com.google.firebase.firestore.FirebaseFirestore

class Topics : AppCompatActivity() {
    lateinit var binding: ActivityTopicsBinding
    private var  db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicsBinding.inflate(layoutInflater)
        recyclerView()
        setContentView(binding.root)
    }
    private fun recyclerView() {
        val list: ArrayList<ModelTopics> = ArrayList()
        val adapter = AdapterTopics(list)
        db.collection("Topic")
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                val documentSnapshotList = queryDocumentSnapshots.documents
                for (a in documentSnapshotList) {
                    list.add(
                        ModelTopics(
                            a.get("").toString()
                        ))
                    adapter.notifyItemChanged(list.size - 1)
                }
            }
    }
}
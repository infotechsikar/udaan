package com.dr.udaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dr.udaan.collections.AdapterTopics
import com.dr.udaan.collections.ModelTopics
import com.dr.udaan.databinding.ActivityTopicBinding
import com.google.firebase.firestore.FirebaseFirestore

class Topic : AppCompatActivity() {
    lateinit var binding: ActivityTopicBinding
    private var  db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        recyclerView()
        setContentView(R.layout.activity_topic)
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
                            a.get("hindi").toString()
                        )
                    )
                    adapter.notifyItemChanged(list.size - 1)
                }
            }
    }
}
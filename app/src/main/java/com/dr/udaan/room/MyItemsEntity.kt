package com.dr.udaan.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_items")
  data class MyItemsEntity (
    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "")
    val documentId: String,
//    @ColumnInfo(name = "")
    val itemType: String,
//    @ColumnInfo(name = "")
    val orderId: String,
//    @ColumnInfo(name = "")
    val price: Int,
//    @ColumnInfo(name = "")
    val expiry: Long,
//    @ColumnInfo(name = "")
    val timestamp: Long,
)

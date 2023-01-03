package com.dr.udaan.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyItemsDao {

    @Insert
    suspend fun insert(entity: MyItemsEntity)

    @Update
    suspend fun update(entity: MyItemsEntity)

    @Query("DELETE FROM my_items")
    fun delete()

   // @Query("DELETE FROM my_items WHERE documentId == :id")
   // fun deleteById(id: String)

   // @Query("SELECT * From my_items WHERE itemType == :itemType")
   // fun getMyTests(itemType: String = ""): List<MyItemsEntity>

   // @Query("SELECT * From my_items WHERE itemType ==:itemType AND documentId ==:itemId AND expiry >=:cTIM")
   // fun getMyItemById(itemType: String = "", itemId: String, cTIM: Long = System.currentTimeMillis()): List<MyItemsEntity>

}
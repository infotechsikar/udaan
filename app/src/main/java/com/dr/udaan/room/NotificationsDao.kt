package com.dr.udaan.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotificationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NotificationEntity)

    @Update
    suspend fun update(entity: NotificationEntity)

    @Query("DELETE FROM notifications")
    fun deleteAll()

    @Query("DELETE FROM notifications WHERE `id` ==:id")
    fun deleteTodayRecords(id: String)

    @Query("SELECT *From notifications")
    fun getNotifications(): List<NotificationEntity>

    @Query("SELECT *From notifications")
    fun getNotificationsLive(): LiveData<List<NotificationEntity>>

}
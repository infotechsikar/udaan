package com.dr.udaan.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dr.udaan.util.Const
import com.dr.udaan.util.Const.NOTIFICATIONS

@Entity(tableName = NOTIFICATIONS)
data class NotificationEntity (
    @ColumnInfo(name = Const.ID)
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = Const.IMAGE_URL)
    val image: String,
    @ColumnInfo(name = Const.TITLE)
    val title : String,
    @ColumnInfo(name = Const.CONTENT)
    val content: String,
    @ColumnInfo(name = Const.DATE_TIME)
    val dateTime: Long
)

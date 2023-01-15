package com.dr.udaan.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dr.udaan.api.retrofit.Pojo.BlogData
import com.dr.udaan.api.retrofit.Pojo.TestData

@Database(entities = [UserData::class, TestData::class, Note::class, NotificationEntity::class, BlogData::class], version = 9)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userData() : UserDataDao
    abstract fun tests() : TestsDao
    abstract fun noteDao(): NoteDao
    abstract fun notificationDao() : NotificationsDao
    abstract fun blogData() : BlogsDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, MyDatabase::class.java, "udaan_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}
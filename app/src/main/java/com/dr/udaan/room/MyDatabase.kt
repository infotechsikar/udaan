package com.dr.udaan.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dr.udaan.api.retrofit.Pojo.TestData

@Database(entities = [UserData::class, TestData::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userData() : UserDataDao
    abstract fun tests() : TestsDao

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
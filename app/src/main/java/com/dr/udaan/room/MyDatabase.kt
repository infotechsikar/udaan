package com.dr.udaan.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [MyItemsEntity::class], version = 0)
 abstract class MyDatabase:RoomDatabase() {

   abstract fun myItemsDao(): MyItemsDao

   companion object {

       private var INSTANCE :MyDatabase? = null
       fun getDatabase(context: Context):MyDatabase {

           if (INSTANCE == null) {
               synchronized(this) {
                   INSTANCE =
                       Room.databaseBuilder(context,MyDatabase::class.java, "user_database_jss")
                           .fallbackToDestructiveMigration()
                           .allowMainThreadQueries()
                           .build()
               }
           }
           return INSTANCE!!
       }
   }

}
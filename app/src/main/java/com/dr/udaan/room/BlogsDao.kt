package com.dr.udaan.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dr.udaan.api.retrofit.Pojo.BlogData

@Dao
interface BlogsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BlogData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(entity: List<BlogData>)

    @Update
    suspend fun update(entity: BlogData)

    @Query("DELETE FROM blogs WHERE is_saved == 0")
    fun deleteAllExceptSaved()

    @Query("DELETE FROM blogs")
    fun deleteEverything()

    @Query("DELETE FROM blogs WHERE id ==:id")
    fun delete(id: Int)

    @Query("SELECT *From blogs WHERE is_saved == 1")
    fun getSavedBlogs(): List<BlogData>

    @Query("SELECT *From blogs WHERE is_saved == 1 AND id=:id LIMIT 1")
    fun getBlogById(id: Int): BlogData?

    @Query("SELECT *From blogs")
    fun getAllBlogsLive(): LiveData<List<BlogData>>

    @Query("SELECT *From blogs WHERE is_saved = 1")
    fun getSavedBlogsLive(): LiveData<List<BlogData>>
    
}
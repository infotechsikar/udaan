package com.dr.udaan.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dr.udaan.api.retrofit.Pojo.TestData

@Dao
interface UserDataDao {

    @Insert
    suspend fun insert(entity: UserData)

    @Update
    suspend fun update(entity: UserData)

    @Query("DELETE FROM userData")
    fun deleteAll()

    @Query("DELETE FROM userData WHERE id ==:id")
    fun delete(id: String)

    @Query("SELECT *From userData")
    fun getUserData(): List<UserData>


}

@Dao
interface TestsDao {

    @Insert
    suspend fun insert(entity: TestData)

    @Update
    suspend fun update(entity: TestData)

    @Query("DELETE FROM tests WHERE is_saved == 0")
    fun deleteAllExceptSaved()

    @Query("DELETE FROM tests")
    fun deleteEverything()

    @Query("DELETE FROM tests WHERE id ==:id")
    fun delete(id: String)

    @Query("SELECT *From tests")
    fun getTests(): List<TestData>

    @Query("SELECT *From tests")
    fun getTestsLive(): LiveData<List<TestData>>

    @Query("SELECT *From tests WHERE is_saved = 1")
    fun getSavedTestLive(): LiveData<List<TestData>>

}

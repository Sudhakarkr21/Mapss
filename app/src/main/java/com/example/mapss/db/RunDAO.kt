package com.example.mapss.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertionRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run) : Boolean

    @Query("Select * from running_table Order by timestamp DESC")
    fun getAllRunsSortedByDate() : LiveData<List<Run>>

    @Query("Select * from running_table Order by timeImMills DESC")
    fun getAllRunSortedByTimeInMillis() : LiveData<List<Run>>

    @Query("Select * from running_table Order by caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned() : LiveData<List<Run>>

    @Query("Select * from running_table Order by avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed() : LiveData<List<Run>>

    @Query("Select * from running_table Order by distanceInMeters DESC")
    fun getAllRunsSortedByDistance() : LiveData<List<Run>>

    @Query("SELECT SUM(timeImMills) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance(): LiveData<Int >

    @Query("SELECT AVG(avgSpeedInKMH) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>
}
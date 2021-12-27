package com.eng_hussein_khalaf066336.searchremotejob.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eng_hussein_khalaf066336.searchremotejob.model.JobToSave

@Dao
interface FavoriteJobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJop(Job:JobToSave)
    @Query("SELECT * FROM fav_job ORDER BY id DESC")
    fun getAllFavoriteJobs():LiveData<List<JobToSave>>
    @Delete
    suspend fun deleteFavoriteJob(Job: JobToSave)
}
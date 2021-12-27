package com.eng_hussein_khalaf066336.searchremotejob.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eng_hussein_khalaf066336.searchremotejob.api.RetrofitInstance
import com.eng_hussein_khalaf066336.searchremotejob.db.FavoriteJobDatabase
import com.eng_hussein_khalaf066336.searchremotejob.model.JobToSave
import com.eng_hussein_khalaf066336.searchremotejob.model.RemoteJob
import retrofit2.Call
import retrofit2.Response

class RemoteJobRepository(private val db:FavoriteJobDatabase){
    private val remoteJobService=RetrofitInstance.api
    private val remoteJobResponseLiveData:MutableLiveData<RemoteJob> = MutableLiveData()
    private val searchJobResponseLiveData:MutableLiveData<RemoteJob> = MutableLiveData()
    init {
        getRemoteJobResponse()
    }
    private fun getRemoteJobResponse() {

        remoteJobService.getRemoteJob().enqueue(
            object : retrofit2.Callback<RemoteJob> {
                override fun onResponse(call: Call<RemoteJob>, response: Response<RemoteJob>) {
                    if (response.body() != null) {
                        remoteJobResponseLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<RemoteJob>, t: Throwable) {
                    remoteJobResponseLiveData.postValue(null)
                    Log.d("error getJob", t.message.toString())
                }
            })
    }

    fun getRemoteJobResponseLiveData():MutableLiveData<RemoteJob>
    {
        return remoteJobResponseLiveData
    }
     fun searchJobResponse(query:String?) {

        remoteJobService.searchRemoteJob(query).enqueue(
            object : retrofit2.Callback<RemoteJob> {
                override fun onResponse(call: Call<RemoteJob>, response: Response<RemoteJob>) {
                    searchJobResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<RemoteJob>, t: Throwable) {
                    searchJobResponseLiveData.postValue(null)
                    Log.d("error search", t.message.toString())
                }
            })
    }
    fun searchJobResponseLiveData():MutableLiveData<RemoteJob>
    {
        return searchJobResponseLiveData
    }
    suspend fun addFavoriteJob(job:JobToSave)=db.getFavoriteJobs().addFavoriteJop(job)
    suspend fun deleteFavoriteJob(job:JobToSave)=db.getFavoriteJobs().deleteFavoriteJob(job)
    fun getAllFavJob()=db.getFavoriteJobs().getAllFavoriteJobs()

}
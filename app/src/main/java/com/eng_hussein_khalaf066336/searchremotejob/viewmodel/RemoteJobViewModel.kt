package com.eng_hussein_khalaf066336.searchremotejob.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eng_hussein_khalaf066336.searchremotejob.model.JobToSave
import com.eng_hussein_khalaf066336.searchremotejob.repository.RemoteJobRepository
import kotlinx.coroutines.launch

class RemoteJobViewModel(
    app:Application,
    private val remoteJobRepository: RemoteJobRepository
) :AndroidViewModel(app) {
    fun remoteResult()=remoteJobRepository.getRemoteJobResponseLiveData()
    fun addFavoriteJob(job:JobToSave)=viewModelScope.launch {
        remoteJobRepository.addFavoriteJob(job)
    }
    fun deleteFavJob(job:JobToSave)=viewModelScope.launch {
        remoteJobRepository.deleteFavoriteJob(job)
    }
    fun getAllFavJobs()=remoteJobRepository.getAllFavJob()
    fun searchRemoteJob(query:String?)=remoteJobRepository.searchJobResponse(query)
    fun searchResult()=remoteJobRepository.searchJobResponseLiveData()
}
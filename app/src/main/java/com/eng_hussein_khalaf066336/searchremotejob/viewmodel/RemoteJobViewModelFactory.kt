package com.eng_hussein_khalaf066336.searchremotejob.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eng_hussein_khalaf066336.searchremotejob.repository.RemoteJobRepository

class RemoteJobViewModelFactory(
    val app:Application,
    private val remoteJobRepository: RemoteJobRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RemoteJobViewModel(app,remoteJobRepository) as T
    }
}
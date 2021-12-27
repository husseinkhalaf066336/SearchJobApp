package com.eng_hussein_khalaf066336.searchremotejob.api

import com.eng_hussein_khalaf066336.searchremotejob.model.RemoteJob
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteJobAPI {

    @GET("remote-jobs")
    fun getRemoteJob(): Call<RemoteJob>

    @GET("remote-jobs")
    fun searchRemoteJob(@Query("search") query: String?): Call<RemoteJob>
}
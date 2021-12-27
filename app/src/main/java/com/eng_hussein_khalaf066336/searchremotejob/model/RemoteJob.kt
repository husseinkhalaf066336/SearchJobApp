package com.eng_hussein_khalaf066336.searchremotejob.model

import com.google.gson.annotations.SerializedName

class RemoteJob(
    @SerializedName("job-count")
    val jobCount: Int?,
    val jobs: List<Job>?,
    val legalNotice: String?
) {
}
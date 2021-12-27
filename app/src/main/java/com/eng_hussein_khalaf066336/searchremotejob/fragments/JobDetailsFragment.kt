package com.eng_hussein_khalaf066336.searchremotejob.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.eng_hussein_khalaf066336.searchremotejob.MainActivity
import com.eng_hussein_khalaf066336.searchremotejob.R
import com.eng_hussein_khalaf066336.searchremotejob.databinding.FragmentJobDetailsBinding
import com.eng_hussein_khalaf066336.searchremotejob.model.Job
import com.eng_hussein_khalaf066336.searchremotejob.model.JobToSave
import com.eng_hussein_khalaf066336.searchremotejob.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar

class JobDetailsFragment : Fragment(R.layout.fragment_job_details) {
    private var _binding:FragmentJobDetailsBinding?=null
    private val binding get() = _binding!!
    private lateinit var currentJob:Job
    private val args:JobDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: RemoteJobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding=FragmentJobDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        currentJob=args.job!!
        setUpWebView()
        binding.fabAddFavorite.setOnClickListener{
            addFavoriteJob(view)
        }
    }

    private fun addFavoriteJob(view: View) {
        val favJob=JobToSave(
            0,
            currentJob.candidateRequiredLocation, currentJob.category,
            currentJob.companyLogoUrl, currentJob.companyName,
            currentJob.description, currentJob.id, currentJob.jobType,
            currentJob.publicationDate, currentJob.salary, currentJob.title, currentJob.url
        )
        viewModel.addFavoriteJob(favJob)
        Snackbar.make(view,"Job saved successfully",Snackbar.LENGTH_LONG).show()
    }

    private fun setUpWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob.url?.let { loadUrl(it) }
        }
        binding.webView.settings.apply {
            javaScriptEnabled = true
            setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false
            textZoom = 100
            blockNetworkImage = false
            loadsImagesAutomatically = true
        }
    }
        override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

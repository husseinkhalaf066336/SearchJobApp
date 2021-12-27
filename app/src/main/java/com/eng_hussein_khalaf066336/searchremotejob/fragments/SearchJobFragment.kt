package com.eng_hussein_khalaf066336.searchremotejob.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.eng_hussein_khalaf066336.searchremotejob.MainActivity
import com.eng_hussein_khalaf066336.searchremotejob.R
import com.eng_hussein_khalaf066336.searchremotejob.adapters.RemoteJobAdapter
import com.eng_hussein_khalaf066336.searchremotejob.databinding.FragmentSearchJobBinding
import com.eng_hussein_khalaf066336.searchremotejob.model.Job
import com.eng_hussein_khalaf066336.searchremotejob.utils.Constants
import com.eng_hussein_khalaf066336.searchremotejob.viewmodel.RemoteJobViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchJobFragment : Fragment(R.layout.fragment_search_job) {

    private var _binding: FragmentSearchJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var jobAdapter: RemoteJobAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchJobBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        if (Constants.isNetworkAvailable(requireContext())) {
            searchJob()
            setUpRecyclerView()
        }
        else{
            Toast.makeText(requireContext(),"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }
    private fun searchJob() {
        binding.etSearch.addTextChangedListener { Text ->
            MainScope().launch {
                Text?.let {
                    if (Text.toString().isNotEmpty()) {
                        viewModel.searchRemoteJob(Text.toString())
                    }
                }
            }

        }
    }

        private fun setUpRecyclerView() {
            jobAdapter = RemoteJobAdapter()
            binding.rvSearchJobs.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = jobAdapter
            }
            viewModel.searchResult().observe(viewLifecycleOwner, { remoteJob ->
                jobAdapter.differ.submitList(remoteJob.jobs)

            })

        }


        override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

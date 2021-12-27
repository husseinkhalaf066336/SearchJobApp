package com.eng_hussein_khalaf066336.searchremotejob.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.eng_hussein_khalaf066336.searchremotejob.MainActivity
import com.eng_hussein_khalaf066336.searchremotejob.R
import com.eng_hussein_khalaf066336.searchremotejob.adapters.RemoteJobAdapter
import com.eng_hussein_khalaf066336.searchremotejob.databinding.FragmentRemoteJobsBinding
import com.eng_hussein_khalaf066336.searchremotejob.utils.Constants
import com.eng_hussein_khalaf066336.searchremotejob.viewmodel.RemoteJobViewModel

class RemoteJobsFragment : Fragment(R.layout.fragment_remote_jobs),
    SwipeRefreshLayout.OnRefreshListener {
    private var _binding: FragmentRemoteJobsBinding? = null
    private val binding get() = _binding!!
    private lateinit var remoteJobViewModel: RemoteJobViewModel
    private lateinit var jobAdapter: RemoteJobAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemoteJobsBinding.inflate(
            inflater,
            container,
            false
        )

        swipeRefreshLayout = binding.swipeContainer
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN, Color.RED, Color.BLUE, Color.DKGRAY)
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            setUpRecyclerView()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        remoteJobViewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        jobAdapter = RemoteJobAdapter()
        binding.rvRemoteJobs.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL
                ) {})
            adapter = jobAdapter
        }
        fetchingData()
    }

    private fun fetchingData() {
        if (Constants.isNetworkAvailable(requireContext())) {
            remoteJobViewModel.remoteResult().observe(viewLifecycleOwner, { remoteJob ->
                if (remoteJob != null) {
                    jobAdapter.differ.submitList(remoteJob.jobs)
                    swipeRefreshLayout.isRefreshing=false
                } else {
                    Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
                    swipeRefreshLayout.isRefreshing=false
                }
            })
        } else {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onRefresh() {
        setUpRecyclerView()
    }
}

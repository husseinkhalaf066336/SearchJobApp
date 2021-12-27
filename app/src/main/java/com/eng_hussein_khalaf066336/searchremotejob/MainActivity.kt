package com.eng_hussein_khalaf066336.searchremotejob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eng_hussein_khalaf066336.searchremotejob.databinding.ActivityMainBinding
import com.eng_hussein_khalaf066336.searchremotejob.db.FavoriteJobDatabase
import com.eng_hussein_khalaf066336.searchremotejob.repository.RemoteJobRepository
import com.eng_hussein_khalaf066336.searchremotejob.viewmodel.RemoteJobViewModel
import com.eng_hussein_khalaf066336.searchremotejob.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RemoteJobViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title=""

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val remoteJobRepository= RemoteJobRepository(FavoriteJobDatabase(this))
        val viewModelFactory=RemoteJobViewModelFactory(application,remoteJobRepository)
        viewModel=ViewModelProvider(this,viewModelFactory).get(RemoteJobViewModel::class.java)

    }
}

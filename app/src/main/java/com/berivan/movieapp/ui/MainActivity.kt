package com.berivan.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berivan.movieapp.R
import com.berivan.movieapp.cammon.viewBinding
import com.berivan.movieapp.data.model.MovieResponse
import com.berivan.movieapp.databinding.ActivityMainBinding
import com.berivan.movieapp.util.jsonToDataClassModel


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModel()
        setJsonRawData()
        initObserver()


    }

    private val movieDataObserver: Observer<MovieResponse> = Observer {

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    private fun setJsonRawData() {
        viewModel.initData(jsonToDataClassModel(R.raw.db))
    }

    private fun initObserver() {
        viewModel.apply {
            dataLiveData.observe(this@MainActivity, movieDataObserver)
        }


    }



}





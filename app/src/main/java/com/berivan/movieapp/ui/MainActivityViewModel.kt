package com.berivan.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berivan.movieapp.data.model.MovieResponse
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    private val mutableMovieData = MutableLiveData<MovieResponse>()
    val dataLiveData : LiveData<MovieResponse> get() = mutableMovieData

    fun initData(data: MovieResponse) = viewModelScope.launch {
        data.let(mutableMovieData::postValue)
    }
}
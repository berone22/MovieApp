package com.berivan.movieapp.ui.movie.watch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berivan.movieapp.data.model.MovieItem
import com.berivan.movieapp.data.model.MovieResponse
import kotlinx.coroutines.launch

class WatchFragmentViewModel: ViewModel() {

    val mutableMovieData = MutableLiveData<MutableList<MovieItem>>()

    fun initData(data: MovieResponse) = viewModelScope.launch {
        data.movies.toMutableList().let(mutableMovieData::postValue)
    }
}
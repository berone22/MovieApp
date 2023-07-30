package com.berivan.movieapp.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berivan.movieapp.R
import com.berivan.movieapp.cammon.viewBinding
import com.berivan.movieapp.data.model.MovieItem
import com.berivan.movieapp.databinding.FragmentWatchedMovieListBinding
import com.berivan.movieapp.ui.movie.watch.WatchFragmentViewModel
import com.berivan.movieapp.util.LocalDataManager
import com.berivan.movieapp.util.jsonToDataClassModel


class WatchedMovieListFragment : Fragment(R.layout.fragment_watched_movie_list) {

    private val binding by viewBinding(FragmentWatchedMovieListBinding::bind)

    private lateinit var viewModel : WatchFragmentViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setJsonRawData()
        initObservers()

    }
    private val movieDataObserver: Observer<MutableList<MovieItem>> = Observer { _data ->
        when (LocalDataManager.getInstance().localListData == null) {
            true -> _data.filter { it.isClicked == true }
                .also { LocalDataManager.getInstance().localListData = _data }

            else -> LocalDataManager.getInstance().localListData?.filter { it.isClicked == true }
        }?.let(::initAdapter)
    }

    private fun initAdapter(data: List<MovieItem>) {
        movieAdapter = MovieAdapter(data, ::checkedItem, ::clickItem)
        binding.rvMovieList.adapter = movieAdapter
    }
    private fun initObservers() {
        viewModel.apply {
            mutableMovieData.observe(viewLifecycleOwner, movieDataObserver)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[WatchFragmentViewModel::class.java]
    }

    private fun setJsonRawData() {
        viewModel.initData(requireActivity().jsonToDataClassModel(R.raw.db))
    }
    private fun checkedItem(data: MovieItem, bool: Boolean) {
        if (bool == false) {
            val mutableList = LocalDataManager.getInstance().localListData?.toMutableList()
            mutableList?.find { it.id == data.id }?.isClicked = false
            LocalDataManager.getInstance().localListData = mutableList
        }
    }

    private fun clickItem(data: MovieItem) {

    }

}

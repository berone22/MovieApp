package com.berivan.movieapp.ui.movie.watch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.berivan.movieapp.R
import com.berivan.movieapp.cammon.viewBinding
import com.berivan.movieapp.data.model.MovieItem
import com.berivan.movieapp.data.model.MovieResponse
import com.berivan.movieapp.databinding.FragmentWatchMovieListBinding
import com.berivan.movieapp.ui.movie.MovieAdapter
import com.berivan.movieapp.util.LocalDataManager
import com.berivan.movieapp.util.jsonToDataClassModel


class WatchMovieListFragment : Fragment(R.layout.fragment_watch_movie_list) {

    private val binding by viewBinding(FragmentWatchMovieListBinding::bind)
    private lateinit var viewModel: WatchFragmentViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setJsonRawData()
        initObservers()
binding.btContinue.setOnClickListener {
    Navigation.findNavController(it).navigate(R.id.watchedMovieListFragment)
}
    }

    private val movieDataObserver: Observer<MutableList<MovieItem>> = Observer { _data ->
        when (LocalDataManager.getInstance().localListData == null) {
            true -> _data.filter { it.isClicked == false }
                .also { LocalDataManager.getInstance().localListData = _data }

            else -> LocalDataManager.getInstance().localListData?.filter { it.isClicked == false }
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
        if (bool == true) {
            val mutableList = LocalDataManager.getInstance().localListData?.toMutableList()
            mutableList?.find { it.id == data.id }?.isClicked = true
            LocalDataManager.getInstance().localListData = mutableList
        }
    }

    private fun clickItem(data: MovieItem) {

    }


}
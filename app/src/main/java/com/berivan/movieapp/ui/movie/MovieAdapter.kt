package com.berivan.movieapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.berivan.movieapp.data.model.MovieItem
import com.berivan.movieapp.data.model.MovieResponse

import com.berivan.movieapp.databinding.ItemMovieBinding
import com.berivan.movieapp.util.loadImage

class MovieAdapter(
    private val dataItem : List<MovieItem>,
    private val clickCheckBox: (MovieItem,bool : Boolean) -> Unit,
    private val clickItem: (MovieItem) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieItem) {
            with(binding) {
                ivMovie.loadImage(movie.posterUrl)
                tvMovieTitle.text = movie.title
                cbMovie.isChecked = movie.isClicked
                cbMovie.setOnCheckedChangeListener { _, bool ->
                    clickCheckBox.invoke(movie, bool)
                }
                binding.root.setOnClickListener {
                    clickItem.invoke(movie)
                }
            }
        }
    }
}

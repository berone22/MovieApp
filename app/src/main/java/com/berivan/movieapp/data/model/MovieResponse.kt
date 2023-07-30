package com.berivan.movieapp.data.model

data class MovieResponse(
    val genres: List<String>,
    val movies: List<MovieItem>
)
package com.berivan.movieapp.data.model

data class MovieItem(
    val actors: String,
    val director: String,
    val genres: List<String>,
    val id: Int,
    val plot: String,
    val posterUrl: String,
    val runtime: String,
    val title: String,
    val year: String,
    var isClicked: Boolean = false
)
package com.berivan.movieapp.util

import android.content.Context
import android.widget.CheckBox
import android.widget.ImageView
import com.berivan.movieapp.R
import com.berivan.movieapp.data.model.MovieItem
import com.berivan.movieapp.data.model.MovieResponse
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import java.io.InputStream

fun Context.jsonToDataClassModel(file: Int): MovieResponse {
    val input : InputStream =  resources.openRawResource(file)
    val b = ByteArray(input.available())
    input.read(b)

    val json = String(b)

    GsonBuilder().create()
    return GsonBuilder().create().fromJson(json, MovieResponse::class.java)
}
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo)
        .into(this)
}


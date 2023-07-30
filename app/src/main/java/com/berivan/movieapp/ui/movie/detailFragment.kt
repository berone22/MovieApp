package com.berivan.movieapp.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.berivan.movieapp.R
import com.berivan.movieapp.cammon.viewBinding
import com.berivan.movieapp.databinding.FragmentDetailBinding



class detailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}





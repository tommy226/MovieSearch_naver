package com.sungbin.movienaver.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungbin.movienaver.R
import com.sungbin.movienaver.adapter.MovieRecyclerAdapter
import com.sungbin.movienaver.data.MovieItem
import com.sungbin.movienaver.viewmodel.MovieSearchViewModel

object BindingConversions {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String){
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_baseline_not_interested_24)
            .into(imageView)
    }

    @BindingAdapter("app:movieList", "app:viewmodel")
    @JvmStatic
    fun RecyclerView.movieList(movieList: List<MovieItem>, viewModel: MovieSearchViewModel){
        val MovieRecyclerAdapter = MovieRecyclerAdapter(viewModel)

        this.apply {
            adapter = MovieRecyclerAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        MovieRecyclerAdapter.items = movieList.toMutableList()
        MovieRecyclerAdapter.notifyDataSetChanged()
    }
}
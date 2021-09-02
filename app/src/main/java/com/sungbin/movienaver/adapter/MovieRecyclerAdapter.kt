package com.sungbin.movienaver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.movienaver.data.MovieItem
import com.sungbin.movienaver.databinding.ActivityMainBinding
import com.sungbin.movienaver.databinding.ItemMovieBinding
import com.sungbin.movienaver.viewmodel.MovieSearchViewModel

class MovieRecyclerAdapter(val viewModel: MovieSearchViewModel) : RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>(){
    var items = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.bind(data, viewModel)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: MovieItem, viewModel: MovieSearchViewModel){
            binding.data = data
            binding.vm = viewModel
            binding.executePendingBindings()
        }
    }
}
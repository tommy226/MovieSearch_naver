package com.sungbin.movienaver.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.sungbin.movienaver.BaseActivity
import com.sungbin.movienaver.R
import com.sungbin.movienaver.databinding.ActivityMainBinding
import com.sungbin.movienaver.viewmodel.MovieSearchViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }

        viewModel.getMovie()
    }
}
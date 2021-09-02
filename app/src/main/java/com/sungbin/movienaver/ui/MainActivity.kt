package com.sungbin.movienaver.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sungbin.movienaver.BaseActivity
import com.sungbin.movienaver.R
import com.sungbin.movienaver.databinding.ActivityMainBinding
import com.sungbin.movienaver.util.EventObserver
import com.sungbin.movienaver.util.showToast
import com.sungbin.movienaver.viewmodel.MovieSearchViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val TAG = MainActivity::class.java.simpleName

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }

        viewModel.toast.observe(this, EventObserver { toast ->
            showToast(toast)
        })

        viewModel.liveUri.observe(this, Observer { uri ->
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
    }
}
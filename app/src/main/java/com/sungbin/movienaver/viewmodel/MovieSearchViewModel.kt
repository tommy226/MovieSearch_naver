package com.sungbin.movienaver.viewmodel

import androidx.lifecycle.ViewModel
import com.sungbin.movienaver.repository.MovieRepository
import com.sungbin.movienaver.util.customEnqueue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieSearchViewModel : ViewModel() {
    private val TAG = MovieSearchViewModel::class.java.simpleName
    private val repo = MovieRepository()

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    fun getMovie() = viewModelScope.launch {
        repo.getMovieInfo("킹덤",20).customEnqueue(
            onSuccess = {},
            onFailure = {},
            onError = {}
        )
    }
}
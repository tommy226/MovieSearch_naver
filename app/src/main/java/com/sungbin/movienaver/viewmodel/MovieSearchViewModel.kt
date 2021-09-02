package com.sungbin.movienaver.viewmodel

import android.net.Uri
import androidx.lifecycle.*
import com.sungbin.movienaver.data.MovieItem
import com.sungbin.movienaver.data.MovieSearchResponse
import com.sungbin.movienaver.repository.MovieRepository
import com.sungbin.movienaver.util.Event
import com.sungbin.movienaver.util.ListLivedata
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

    private val _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>>
        get() = _toast

    val inputMovietitle = MutableLiveData("")

    var movieList = ListLivedata<MovieItem>()
    var liveUri = MutableLiveData<Uri>()

    fun getMovie(search: String) = viewModelScope.launch {
        val response = repo.getMovieInfo(search)

        response.customEnqueue(
            onSuccess = {
                if (it.code() == 200) {
                    movieList.clear()
                    it.body()?.items?.let { items -> movieList.addAll(items.toMutableList()) }
                }
            },
            onFailure = {
                _toast.value = Event("Server fail")
            },
            onError = {
                _toast.value = Event("Server error")
            }
        )
    }

    fun linkClick(uri: String) = viewModelScope.launch {
        liveUri.value = Uri.parse(uri)
    }

    val isBlank: LiveData<Boolean>
        get() = blankCheck()

    private fun blankCheck() = Transformations.map(inputMovietitle) { edittext ->
        !edittext.isNullOrBlank()
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
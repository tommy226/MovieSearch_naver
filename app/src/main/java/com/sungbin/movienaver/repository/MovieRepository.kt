package com.sungbin.movienaver.repository

import com.sungbin.movienaver.network.NaverImpl

class MovieRepository {
    fun getMovieInfo(searchText: String, display: Int) = NaverImpl.service.getMovieInfoList(searchText, 2)
}
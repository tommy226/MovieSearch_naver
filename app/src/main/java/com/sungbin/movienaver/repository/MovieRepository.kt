package com.sungbin.movienaver.repository

import com.sungbin.movienaver.network.NaverImpl

class MovieRepository {
    fun getMovieInfo(searchText: String) = NaverImpl.service.getMovieInfoList(searchText, 20)
}
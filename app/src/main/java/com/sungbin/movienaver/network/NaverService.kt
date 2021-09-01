package com.sungbin.movienaver.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {
    @GET("v1/search/movie.json")
    fun getMovieInfoList(
        @Query("query") searchText: String,
        @Query("display") displayCount: Int
    ): Call<ResponseBody>
}
package com.sungbin.movienaver.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NaverImpl {
    private const val BASE_URL = "https://openapi.naver.com/"
    private const val CLIENT_ID = ""
    private const val CLIENT_SECRET = ""

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    val headerInterceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("X-Naver-Client-Id", CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
            .build()
        return@Interceptor it.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor())
        .addInterceptor(headerInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: NaverService = retrofit.create(NaverService::class.java)
}
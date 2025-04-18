package com.example.gallery.data.retrofit

import com.example.gallery.domain.model.ImageItemDto
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumApiService {
    @GET("v2/list")
    fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20
    ): Single<List<ImageItemDto>>
}

object RetrofitClient {
    private const val BASE_URL = "https://picsum.photos/"

    val apiService: PicsumApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PicsumApiService::class.java)
    }
}
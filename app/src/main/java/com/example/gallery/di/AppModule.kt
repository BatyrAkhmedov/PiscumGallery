package com.example.gallery.di

import com.example.gallery.data.mapper.ImageItemMapper
import com.example.gallery.data.repository.RepositoryImpl
import com.example.gallery.data.retrofit.PicsumApiService
import com.example.gallery.data.retrofit.RetrofitClient
import com.example.gallery.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: PicsumApiService, mapper: ImageItemMapper): Repository {
        return RepositoryImpl(apiService, mapper)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofitClient: RetrofitClient): PicsumApiService {
        return retrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient
    }

    @Provides
    @Singleton
    fun provideImageItemMapper(): ImageItemMapper {
        return ImageItemMapper()
    }



}
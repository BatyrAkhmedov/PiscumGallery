package com.example.gallery.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gallery.presentation.ImageViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel::class)
    fun bindImageViewModel(viewModel: ImageViewModel): ViewModel

    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}
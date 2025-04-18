package com.example.gallery.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.data.model.ImageItem
import com.example.gallery.domain.model.ImageItemDto
import com.example.gallery.domain.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.random.Random

class ImageViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _images = MutableLiveData<List<ImageItem>>()
    val images: LiveData<List<ImageItem>> get() = _images

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadImages() {
        _isLoading.value = true
        _error.value = null
        val page = Random.nextInt(1, 11)
        disposable.add(
            repository.getImages(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ imageList ->
                    _images.value = imageList
                    _isLoading.value = false
                }, { throwable ->
                    _error.value = throwable.message
                    _isLoading.value = false
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
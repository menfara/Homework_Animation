package com.example.homework_animation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework_animation.models.GalleryItem
import com.example.homework_animation.repositories.GalleryRepository

class GalleryViewModel : ViewModel() {

    private val repository = GalleryRepository()

    private val _galleryItems = MutableLiveData<List<GalleryItem>>()
    val galleryItems: LiveData<List<GalleryItem>> get() = _galleryItems

    init {
        _galleryItems.value = repository.getGalleryItems()
    }
}

package me.admund.uutask.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import me.admund.uutask.domain.ImagesRepository
import me.admund.uutask.domain.Image

class MainViewModel(
    private val imagesRepository: ImagesRepository,
) : ViewModel() {

    companion object {
        const val CLIENT_ID = "gkoGjkJctv1f1u5MnN2R73zk7IoyNL3R9IUVDX3-_Do"
    }

    val images: Flow<List<Image>> = imagesRepository.images()

    init {
        fetchImages()
    }

    private fun fetchImages() {
        imagesRepository.fetchImages(CLIENT_ID, viewModelScope)
    }
}

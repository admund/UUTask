package me.admund.uutask.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import me.admund.uutask.model.ImageModel
import me.admund.uutask.repositories.ImagesRepository

class MainViewModel(
    private val imagesRepository: ImagesRepository
) : ViewModel() {

    companion object {
        const val CLIENT_ID = "gkoGjkJctv1f1u5MnN2R73zk7IoyNL3R9IUVDX3-_Do"
    }

    val images: LiveData<List<ImageModel>> = MutableLiveData()
    private val mutableImages = images as MutableLiveData
    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    fun fetchImages() {
        scope.launch {
            val list = imagesRepository.fetchImages(CLIENT_ID)
            val result = mutableListOf<ImageModel>().apply {
                addAll(list)
                addAll(list)
                addAll(list)
                addAll(list)
                addAll(list)
            }
            launch(Dispatchers.Main) {
                mutableImages.value = result // list
            }
        }
    }
}

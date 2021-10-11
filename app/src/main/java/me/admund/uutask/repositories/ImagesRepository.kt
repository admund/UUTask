package me.admund.uutask.repositories

import me.admund.uutask.model.ImageModel
import me.admund.uutask.retorofit.ImagesService
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val imagesService: ImagesService
) {

    companion object {
        private const val IMAGE_URL = "regular"
    }

    suspend fun fetchImages(clientId: String) =
        imagesService.images(clientId).map {
            ImageModel(it.id, it.urls[IMAGE_URL] ?: "", it.likes)
        }
}

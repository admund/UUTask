package me.admund.uutask.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.admund.uutask.data.ImagesApi
import javax.inject.Inject
import javax.inject.Singleton

interface ImagesRepository {
    fun images(): Flow<List<Image>>
    fun fetchImages(clientId: String, scopeIo: CoroutineScope)
}

@Singleton
class ImagesRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
) : ImagesRepository {

    companion object {
        private const val IMAGE_URL = "regular"
    }

    private val imageMutableStateFlow: MutableStateFlow<List<Image>> = MutableStateFlow(emptyList())

    override fun images(): Flow<List<Image>> = imageMutableStateFlow

    override fun fetchImages(clientId: String, scopeIo: CoroutineScope) {
        scopeIo.launch {
            val imageModelList = imagesApi.images(clientId).map {
                Image(it.id, it.urls[IMAGE_URL] ?: "", it.likes)
            }

            // added just to have more images, to be recycler view scrollable
            val multipliedResult = mutableListOf<Image>().apply {
                repeat(5) {
                    addAll(imageModelList)
                }
            }

            imageMutableStateFlow.value = multipliedResult
        }
    }
}

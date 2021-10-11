package me.admund.uutask.retorofit

data class ImageEntity(
    val id: String,
    val description: String,
    val likes: Long,
    val urls: Map<String, String>
)

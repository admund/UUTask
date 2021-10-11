package me.admund.uutask.retorofit

import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {

    @GET("photos")
    suspend fun images(@Query("client_id") clientId: String): List<ImageEntity>
}

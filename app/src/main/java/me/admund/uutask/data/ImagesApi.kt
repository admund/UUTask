package me.admund.uutask.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("photos")
    suspend fun images(@Query("client_id") clientId: String): List<ImageEntity>
}

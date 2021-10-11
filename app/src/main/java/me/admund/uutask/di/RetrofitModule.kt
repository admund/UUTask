package me.admund.uutask.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.admund.uutask.retorofit.ImagesService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"
    }

    @Provides
    fun provideImagesModule(): ImagesService {
        val okHttp = OkHttpClient.Builder().build()
        val gsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .client(okHttp)
            .build()

        return retrofit.create(ImagesService::class.java)
    }
}

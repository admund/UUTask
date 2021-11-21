package me.admund.uutask.di

import dagger.Binds
import dagger.Module
import me.admund.uutask.domain.ImagesRepository
import me.admund.uutask.domain.ImagesRepositoryImpl

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun providesImageRepository(repository: ImagesRepositoryImpl): ImagesRepository
}

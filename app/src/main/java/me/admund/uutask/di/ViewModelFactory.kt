package me.admund.uutask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.admund.uutask.domain.ImagesRepository
import me.admund.uutask.ui.main.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val imagesRepository: ImagesRepository
) : ViewModelProvider.Factory {

    @ExperimentalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(imagesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


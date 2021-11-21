package me.admund.uutask.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.admund.uutask.databinding.ActivityMainBinding
import me.admund.uutask.di.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLUMNS_NUMBER = 3
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private val imagesAdapter = ImagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            imagesRecyclerView.apply {
                adapter = imagesAdapter
                layoutManager = GridLayoutManager(context, COLUMNS_NUMBER)
            }
        }

        viewModel.images.onEach {
            imagesAdapter.submitList(it)
            binding.loadingProgressBar.isVisible = it.isEmpty()
        }.launchIn(lifecycleScope)
    }
}

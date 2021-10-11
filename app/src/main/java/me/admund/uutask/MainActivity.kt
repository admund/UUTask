package me.admund.uutask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import me.admund.uutask.databinding.ActivityMainBinding
import me.admund.uutask.di.ViewModelFactory
import me.admund.uutask.main.ImagesAdapter
import me.admund.uutask.main.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLUMNS_NUMBER = 4
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainViewModel
    private val imagesAdapter = ImagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            imagesRecyclerView.apply {
                adapter = imagesAdapter
                layoutManager = GridLayoutManager(context, COLUMNS_NUMBER)
            }
        }

        viewModel.images.observe(this) {
            imagesAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchImages()
    }
}

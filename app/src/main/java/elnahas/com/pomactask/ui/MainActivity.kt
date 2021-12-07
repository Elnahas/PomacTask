package elnahas.com.pomactask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import elnahas.com.pomactask.R
import elnahas.com.pomactask.adapter.MostPopularAdapter
import elnahas.com.pomactask.utils.Resource
import elnahas.com.pomactask.viewmodels.MostPopularViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mostPopularViewModel: MostPopularViewModel by viewModels()

    lateinit var mostPopularAdapter: MostPopularAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        setObservers()

    }

    private fun setObservers() {

        lifecycleScope.launchWhenStarted {
            mostPopularViewModel.mostPopularState.collect {
                when (it) {
                    is Resource.Success -> {
                        mostPopularAdapter.differ.submitList(it.data.resultModels)
                        progressBar.isVisible = false
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                    }
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun setupRecyclerView() {
        mostPopularAdapter = MostPopularAdapter()
        recycler_view.apply {
            adapter = mostPopularAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

}
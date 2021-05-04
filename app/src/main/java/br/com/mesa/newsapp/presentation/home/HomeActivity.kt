package br.com.mesa.newsapp.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mesa.newsapp.databinding.ActivityHomeBinding
import br.com.mesa.newsapp.presentation.home.adapter.NewsAdapter
import br.com.mesa.newsapp.util.EndlessRecyclerViewScrollListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val newsAdapter: NewsAdapter by inject()
    private lateinit var newsGridLayoutManager: GridLayoutManager
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupList()
        load()
    }

    private fun setupList() {
        newsGridLayoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            setHasFixedSize(true)
            this.layoutManager = newsGridLayoutManager
        }
        binding.newsRecyclerView.addOnScrollListener(
            object : EndlessRecyclerViewScrollListener(newsGridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    homeViewModel.page = page + 1
                    homeViewModel.getNews()
                }
            }
        )
    }

    private fun load() {
        homeViewModel.getNews()
        homeViewModel.getHighlights()

        homeViewModel.listNews.observe(this, Observer {
            newsAdapter.addNews(it)
        })

        homeViewModel.listSpotlight.observe(this, Observer {
            binding.slider.setImageList(it)
        })

        homeViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                binding.progressHome.visibility = View.VISIBLE
            } else {
                binding.progressHome.visibility = View.INVISIBLE
            }
        })

        homeViewModel.errorConnection.observe(this, Observer { hasError ->
            if (hasError) {
                binding.newsRecyclerView.visibility = View.INVISIBLE
                binding.layoutInternetErrorHome.visibility = View.VISIBLE
            } else {
                binding.newsRecyclerView.visibility = View.VISIBLE
                binding.layoutInternetErrorHome.visibility = View.INVISIBLE
            }
        })

        homeViewModel.errorMessage.observe(this, Observer {
            binding.textViewErrorMessageHome.text = it
        })

//        binding.newsSearch.addTextChangedListener(object : TextWatcher {
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                newsAdapter.filter.filter(s.toString())
//            }
//        })
    }
}
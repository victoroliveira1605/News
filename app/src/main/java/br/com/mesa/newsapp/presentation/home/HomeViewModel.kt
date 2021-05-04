package br.com.mesa.newsapp.presentation.home

import androidx.lifecycle.MutableLiveData
import br.com.mesa.newsapp.data.model.Spotlight
import br.com.mesa.newsapp.data.model.News
import br.com.mesa.newsapp.domain.NewsRepository
import br.com.mesa.newsapp.presentation.base.BaseViewModel
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : BaseViewModel() {
    var page: Int = 1
    private var listNewsHelper: MutableList<News> = mutableListOf()
    val listNews: MutableLiveData<MutableList<News>> = MutableLiveData()

    val listSpotlight: MutableLiveData<MutableList<SlideModel>> = MutableLiveData()
    private var listSpotlightsHelper: MutableList<SlideModel> = mutableListOf()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getNews() {
        isLoading.value = true
        scope.launch {
            try {
                val response = newsRepository.getNews(page,10,null)
                listNewsHelper.addAll(response.data)
                listNews.value = listNewsHelper
                errorConnection.value = false
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }
    }

    fun getHighlights() {
        isLoading.value = true
        scope.launch {
            try {
                val response = newsRepository.getHighlights()
                response.data.forEach {
                    listSpotlightsHelper.add(
                        SlideModel( it.imageUrl)
                    )
                }
                listSpotlight.value = listSpotlightsHelper

                errorConnection.value = false
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }
    }
}
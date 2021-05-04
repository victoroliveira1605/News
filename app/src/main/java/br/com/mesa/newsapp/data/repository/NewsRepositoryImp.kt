package br.com.mesa.newsapp.data.repository

import br.com.mesa.newsapp.data.model.SpotlightResponse
import br.com.mesa.newsapp.data.model.NewsResponse
import br.com.mesa.newsapp.data.remote.NewsService
import br.com.mesa.newsapp.domain.NewsRepository

class NewsRepositoryImp(
    private val apiService: NewsService
) : NewsRepository {
    override suspend fun getNews(current: Int?, page: Int?, published: String?): NewsResponse {
        return apiService.getNews(current, page, published)
    }

    override suspend fun getHighlights(): SpotlightResponse {
        return apiService.getHighlights()
    }
}
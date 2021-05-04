package br.com.mesa.newsapp.domain

import br.com.mesa.newsapp.data.model.SpotlightResponse
import br.com.mesa.newsapp.data.model.NewsResponse

interface NewsRepository {
    suspend fun getNews(current: Int?,page: Int?,published: String?): NewsResponse
    suspend fun getHighlights(): SpotlightResponse

}
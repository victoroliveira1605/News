package br.com.mesa.newsapp.data.remote

import br.com.mesa.newsapp.data.model.SpotlightResponse
import br.com.mesa.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v1/client/news")
    suspend fun getNews(
        @Query("current_page") current: Int?,
        @Query("per_page") page: Int?,
        @Query("published_at") published: String?
        ): NewsResponse

    @GET("v1/client/news/highlights")
    suspend fun getHighlights(
    ): SpotlightResponse

}
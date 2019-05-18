package com.kishore.news.model.network.newsapi

import com.kishore.news.model.network.newsapiresponse.NewsData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsAPI {

    @GET("/v2/top-headlines")
    fun getHeadlines(@QueryMap queryMap: Map<String, String>): Deferred<NewsData>

    @GET("/v2/everything")
    fun getAllNews(@QueryMap queryMap: Map<String, String>): Deferred<NewsData>

    @GET("/v2/sources")
    fun searchMultiple(@QueryMap queryMap: Map<String, String>): Deferred<NewsData>
}
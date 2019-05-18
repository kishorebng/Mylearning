package com.kishore.news.model.network.newsapi

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kishore.news.model.network.newsapiresponse.NewsData
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class NewsRetrofitClient {

    val api_key = "81c918f7c8fe4a2ab0536c595ee26b3c"

    private val newsApi: NewsAPI

    var client = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val httpUrl = original.url()

                    val newHttpUrl = httpUrl
                            .newBuilder()
                            .addQueryParameter(API_KEY, api_key)
                            .build()

                    val requestBuilder = original
                            .newBuilder()
                            .url(newHttpUrl)

                    val request = requestBuilder
                            .build()
                    return chain.proceed(request)
                }
            }).build()

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(NEWS_API_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        newsApi = retrofit.create(NewsAPI::class.java)
    }


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: NewsRetrofitClient? = null

        fun getInstance(): NewsRetrofitClient {
            return instance ?: synchronized(this) {
                instance ?: NewsRetrofitClient().also { instance = it }
            }
        }
    }


    fun getHeadlines(queryMap: Map<String, String>): Deferred<NewsData> {
        return newsApi.getHeadlines(queryMap)
    }

    fun getAllNews(queryMap: Map<String, String>): Deferred<NewsData> {
        return newsApi.getAllNews(queryMap)
    }


}
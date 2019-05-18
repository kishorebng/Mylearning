package com.kishore.news.model.network.newsapiresponse

import com.kishore.news.model.NewsModelUtil
import com.kishore.news.model.database.NewsTable
import java.util.*

class NewsData {

    private var status: String? = null
    private var totalResults: Int? = null
    private var articles: List<Article>? = null


    /**
    Get newsdata  which is only required for UI
     */
    fun toNewsTableData(): Array<NewsTable?> {
        val totalResults = this.totalResults
        var mNewsData: Array<NewsTable?> = arrayOfNulls<NewsTable>(this.articles!!.size)
        for (i in 0 until this.articles!!.size) {
            val data: Article = this.articles!!.get(i)
            val dateTimeMillis = NewsModelUtil.getNormalizedUtcMsForToday() + NewsModelUtil.DAY_IN_MILLIS * i
            val author = data.author
            val title = data.title
            val description = data.description
            val url = data.url
            val urlToImage = data.urlToImage
            val publishedAt = data.publishedAt
            val content = data.content
            val sourceName = data.source!!.name
            mNewsData[i] = NewsTable(Date(dateTimeMillis), author, title,
                    description, url, urlToImage, publishedAt, content,
                    sourceName)
        }

        val asc = Array(5) { i -> (i * i).toString() }
        return mNewsData

    }


    /**
    Get newsdata  which is only required for UI
     */
    fun toNewsTableDataList(isheadline : Int ): List<NewsTable?> {
        val totalResults = this.totalResults
        var mHeadlines: MutableList<NewsTable?> = ArrayList<NewsTable?>()
        for (i in 0 until this.articles!!.size) {
            val data: Article = this.articles!!.get(i)
            val dateTimeMillis = NewsModelUtil.getNormalizedUtcMsForToday() + NewsModelUtil.DAY_IN_MILLIS * i
            val author = data.author
            val title = data.title
            val description = data.description
            val url = data.url
            val urlToImage = data.urlToImage
            val publishedAt = data.publishedAt
            val content = data.content
            val sourceName = data.source!!.name
            mHeadlines.add(NewsTable(Date(dateTimeMillis), author, title,
                    description, url, urlToImage, publishedAt, content,
                    sourceName,isheadline))
        }
        return mHeadlines
    }

}
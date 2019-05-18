package com.kishore.news.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "newsDetail")
data class NewsTable(var date: Date? = null,
                     var author: String? = null,
                     var title: String? = null,
                     var description: String? = null,
                     var url: String? = null,
                     var urlToImage: String? = null,
                     var publishedAt: String? = null,
                     var content: String? = null,
                     var sourceName: String? = null,
                     var isHeadline: Int = 0) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

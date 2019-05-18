package com.kishore.news.viewmodel

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kishore.news.common.NewsUtil
import com.kishore.news.model.NewsRepository
import com.kishore.news.model.database.NewsTable

class NewsDetailsViewModel(newsRepository: NewsRepository, id: Int) : BaseObservable() {

    @Bindable
    var newsDetail: NewsTable? = newsRepository.getNewsDetail(id)

    /*
       @JvmStatic is just work around for Bindingadapter static method fix
    */
    companion object {
        @BindingAdapter("android:src")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                    .load(imageUrl)
                    .into(view)
        }

        @BindingAdapter("localeDate")
        @JvmStatic
        fun localeDate(view: TextView, publishedAt: String?) {
            view.setText(NewsUtil.formatDate(publishedAt))
        }

        @BindingAdapter("content", "description")
        @JvmStatic
        fun content(view: TextView, contentdata: String?, description: String) {
            if (contentdata == null) {
                view.setText(description)
            } else {
                val index = contentdata!!.indexOf("[+")
                if ((index != -1)) {
                    view.setText(contentdata!!.substring(0, index))
                } else {
                    view.setText(contentdata)
                }
            }
        }
    }

}
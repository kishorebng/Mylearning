package com.kishore.news.viewmodel

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kishore.news.R
import com.kishore.news.common.NewsUtil
import com.kishore.news.model.database.NewsTable


class NewsListItemViewModel(newsTable: NewsTable?) : BaseObservable() {

    /*
        @JvmStatic is just work around for Bindingadapter static method fix
     */
    companion object {
        @BindingAdapter("android:src")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide.with(view.context)
                    .load(imageUrl)
                    .error(R.drawable.my_news)
                    .into(view)
        }

        @BindingAdapter("localeDate")
        @JvmStatic
        fun localeDate(view: TextView, publishedAt: String) {
            view.setText(NewsUtil.formatDate(publishedAt))
        }
    }

    @Bindable
    var news: NewsTable? = newsTable

}



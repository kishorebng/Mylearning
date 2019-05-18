package com.kishore.news.viewmodel.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.kishore.news.R
import com.kishore.news.databinding.HeadlineslistItemBinding
import com.kishore.news.databinding.NewslistItemBinding
import com.kishore.news.model.database.NewsTable
import com.kishore.news.view.NewsListFragmentDirections
import com.kishore.news.viewmodel.NewsListItemViewModel


class NewsListAdapter (context: Context, headline:Boolean, clickHandler: NewsListOnItemClickHandler): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> (){

    var newsData : List<NewsTable>? = null
    private var mContext: Context = context
    private var isHeadline : Boolean = headline
    /*
  *  we've defined an interface to handle clicks on items within this Adapter
  */
    private var mClickHandler: NewsListOnItemClickHandler = clickHandler;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var layoutId : Int
        when (isHeadline) {
            true -> layoutId = R.layout.headlineslist_item
            false -> layoutId = R.layout.newslist_item
        }
        val view = LayoutInflater.from(mContext).inflate(layoutId, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
//          if (isHeadline) {
//              return if (newsData == null) 0 else newsData!!.size * 2
//          } else {
//              return if (null == newsData) 0 else newsData!!.size
//          }
        return if (null == newsData) 0 else newsData!!.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var newPostion = position
//        if (isHeadline) {
//            newPostion =  position % newsData!!.size
//        }
        holder.setItemViewModel(NewsListItemViewModel(newsData!!.get(newPostion)))
    }

    override fun onViewAttachedToWindow(holder: NewsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: NewsViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }


    fun updateData(@Nullable data: List<NewsTable>) {
        Log.i("sql","headlines "+ isHeadline +" newsDetail adapter "+data.size)
        this.newsData = data
        notifyDataSetChanged()
    }

    /**
     * The interface that receives onItemClick messages.
     */
    interface NewsListOnItemClickHandler {
        fun onItemClick()
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var binding : NewslistItemBinding? = null
        var headlineBinding : HeadlineslistItemBinding? = null

        init {
            bind()
            view.setOnClickListener(this)
        }

         fun setItemViewModel( itemViewModel : NewsListItemViewModel) {
             if (isHeadline){
                 headlineBinding!!.itemViewModel = itemViewModel
             } else {
                 binding!!.itemViewModel = itemViewModel
             }
         }

         fun bind() {
             if (isHeadline){
                 headlineBinding = DataBindingUtil.bind(itemView)
             } else {
                 binding = DataBindingUtil.bind(itemView)
             }
         }

         fun unbind() {
             if (isHeadline){
                 headlineBinding!!.unbind()
             } else {
                 binding!!.unbind()
             }
         }

        override fun onClick(v: View?) {
//            mClickHandler.onItemClick()

            var newPostion = adapterPosition
//            if (isHeadline) {
//                newPostion =  newPostion % newsData!!.size
//            }
            val direction =
                    NewsListFragmentDirections.actionNewsDetails(newsData!![newPostion].id)
            v!!.findNavController().navigate(direction)
       }
    }
}


package com.kishore.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kishore.news.InjectorUtils
import com.kishore.news.databinding.FragmentNewsdetailsBinding
import com.kishore.news.viewmodel.NewsDetailsViewModel
import android.content.Intent
import android.net.Uri


class NewsDetailsFragment : Fragment() {

    private val args: NewsDetailsFragmentArgs by navArgs()

    private lateinit var mNewsDetailBinding: FragmentNewsdetailsBinding
    private lateinit var mNewsListViewModel: NewsDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mNewsDetailBinding = FragmentNewsdetailsBinding.inflate(inflater, container, false)
//        (activity as AppCompatActivity).supportActionBar?.hide()
 //      (activity as AppCompatActivity).setSupportActionBar(mNewsDetailBinding.toolbarArticle)
//       (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)   // if want to disable title
 //      (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        mNewsDetailBinding.toolbarLayout.setExpandedTitleColor(requireActivity().getColor(android.R.color.transparent))
        return mNewsDetailBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mNewsListViewModel = InjectorUtils.provideNewsDetailsModel(requireActivity().applicationContext, args.newsId)
        mNewsDetailBinding.viewModel = mNewsListViewModel
        mNewsDetailBinding.setHandler(ClickHandler())
//        Snackbar.make(mNewsDetailBinding.root, " Read More...", Snackbar.LENGTH_INDEFINITE)
//                .show()


    }

    inner class ClickHandler {

        fun share(v :View) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this News! \n" + Uri.parse(mNewsDetailBinding.viewModel!!.newsDetail!!.url))
            startActivity(Intent.createChooser(shareIntent, "Share with"))
        }

        fun viewMore(v :View) {
            val viewIntent = Intent()
            viewIntent.action = Intent.ACTION_VIEW
            viewIntent.setData(Uri.parse(mNewsDetailBinding.viewModel!!.newsDetail!!.url))
            startActivity(viewIntent)
        }


    }

}




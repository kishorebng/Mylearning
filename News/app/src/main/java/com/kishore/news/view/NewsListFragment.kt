package com.kishore.news.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.kishore.news.InjectorUtils
import com.kishore.news.NewsApplication
import com.kishore.news.R
import com.kishore.news.common.NewsUtil
import com.kishore.news.common.depndency.NewsSharedPreferenceDagger
import com.kishore.news.common.depndency.NewsSharedPreferenceKoin
import com.kishore.news.databinding.FragmentNewslistBinding
import com.kishore.news.viewmodel.NewsListViewModel
import com.kishore.news.viewmodel.NewsListViewModelFactory
import com.kishore.news.viewmodel.adapter.NewsListAdapter
import org.koin.android.ext.android.inject
import javax.inject.Inject


class NewsListFragment : Fragment(), NewsListAdapter.NewsListOnItemClickHandler, SearchView.OnQueryTextListener {

    lateinit var mContext: Context
    private lateinit var mNewsListBinding: FragmentNewslistBinding
    private lateinit var mNewsListViewModel: NewsListViewModel
    private lateinit var mheadlinesListAdapter: NewsListAdapter
    private lateinit var mNewsListAdapter: NewsListAdapter
    private lateinit var headlineslayoutManager : LinearLayoutManager

    @Inject
    lateinit var mySharedPreferencesDagger: NewsSharedPreferenceDagger

    val mySharedPreferencesKoinDagger: NewsSharedPreferenceKoin by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mNewsListBinding = FragmentNewslistBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return mNewsListBinding.root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
 //     InjectorUtils.getNewsRepository(requireActivity()).prepopulatenews(mContext)

        headlineslayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mNewsListBinding.headlinesList!!.layoutManager = headlineslayoutManager

        mheadlinesListAdapter = NewsListAdapter(requireActivity(),true, this)
        mNewsListBinding.headlinesList.adapter = mheadlinesListAdapter

        val helper = PagerSnapHelper()
        helper.attachToRecyclerView(mNewsListBinding.headlinesList)

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mNewsListBinding.newsList!!.layoutManager = layoutManager

        mNewsListAdapter = NewsListAdapter(requireActivity(), false,this)
        mNewsListBinding.newsList.adapter = mNewsListAdapter

        val factory : NewsListViewModelFactory = InjectorUtils.provideNewsListViewModelFactory(requireActivity().applicationContext)
        mNewsListViewModel = ViewModelProviders.of(this, factory).get(NewsListViewModel::class.java)
        mNewsListViewModel!!.newsdata.observe(this, Observer { newsEntries  ->
            this.mNewsListAdapter!!.updateData(newsEntries!!)
        })

        mNewsListViewModel!!.headlinesnewsdata.observe(this, Observer { newsEntries  ->
            this.mheadlinesListAdapter.updateData(newsEntries!!)
        })

//        mNewsListViewModel!!.headlinesnewsdata.observeForever { newsEntries ->
//            this.mheadlinesListAdapter!!.updateData(newsEntries!!)
//        }
//
//        mNewsListViewModel!!.newsdata.observeForever { newsEntries ->
//
//            this.mNewsListAdapter!!.updateData(newsEntries!!)
//        }

        var application : NewsApplication = requireContext().applicationContext as NewsApplication
        application.newsComponent.injectHere(this)
        val shared= mySharedPreferencesDagger.getStringPreference(mContext.getString(com.kishore.news.R.string.country_entry_key),"")
        if(TextUtils.isEmpty(shared)) {
            mySharedPreferencesDagger.putStringPreference(mContext.getString(com.kishore.news.R.string.country_key),NewsUtil.getCountryCode())
            mySharedPreferencesDagger.putStringPreference(mContext.getString(com.kishore.news.R.string.country_entry_key),NewsUtil.getDisplayCountry())
        }
        Log.i("sql", "prefer "+shared)

        val shared1= mySharedPreferencesKoinDagger.getStringPreference(mContext.getString(com.kishore.news.R.string.country_entry_key),"")

        Log.i("sql", "prefer Koin1 " +shared)

        autoScroll()
    }


    fun autoScroll() {
      //  val firstCompletelyItemVisible = headlineslayoutManager.findFirstCompletelyVisibleItemPosition()
        val speedScroll : Long = 3000
        val handler = Handler()
        val runnable = object : Runnable {
            var visibleItem = 0
            override fun run() {
                if (isVisible && getUserVisibleHint()) {
                    visibleItem =   headlineslayoutManager.findFirstVisibleItemPosition()
                    if (visibleItem == mheadlinesListAdapter.getItemCount()-1) {
                        visibleItem = 0
                        mNewsListBinding.headlinesList.scrollToPosition(0)
                    }
                    if (visibleItem < mheadlinesListAdapter.getItemCount()) {
                        mNewsListBinding.headlinesList.smoothScrollToPosition(++visibleItem)
                    }
                    Log.i("sql","count "+ visibleItem)
                }
                handler.postDelayed(this, speedScroll)
            }
        }
        handler.postDelayed(runnable, speedScroll)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.newslist_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView : SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(NewsListFragmentDirections.actionNewsSettings())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val queryText = "*$query*"
        mNewsListViewModel.searchNews(queryText)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val queryText = "*$newText*"
        mNewsListViewModel.searchNews("%"+newText+"%")
        return true
    }

    override fun onItemClick() {
        Toast.makeText(requireActivity(),"list clicked",Toast.LENGTH_SHORT).show()
    }
}

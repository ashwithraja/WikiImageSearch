package com.mahadream.wikiimagesearch.ui.search

import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.designwebtech.kambala.android.data.network.models.response.Either
import com.mahadream.wikiimagesearch.R
import com.mahadream.wikiimagesearch.common.BaseViewModelFactory
import com.mahadream.wikiimagesearch.common.WikiViewModelProvider
import com.mahadream.wikiimagesearch.data.common.ErrorModel
import com.mahadream.wikiimagesearch.data.remote.SearchResult
import com.mahadream.wikiimagesearch.databinding.ActivityMainBinding
import com.mahadream.wikiimagesearch.ui.adapter.SearchAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.Arrays.stream
import java.util.stream.Collector
import java.util.stream.Collectors
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope {
    private val mAdapter: SearchAdapter by lazy { SearchAdapter() }
    private lateinit var binding: ActivityMainBinding
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    val viewModel: SearchViewModel by lazy {
        androidx.lifecycle.ViewModelProvider(
            this,
            BaseViewModelFactory {
                WikiViewModelProvider.provideDashBoardViewModel()
            }
        ).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUi()
        job = Job()
        setSearchListnerUsinfFlow()
    }

    private fun initUi() {
        binding.rcSearch.layoutManager = GridLayoutManager(this, 2)
        binding.rcSearch.adapter = mAdapter
    }

    override fun onDestroy() {

        job.cancel()
        super.onDestroy()
    }

    private fun setSearchListnerUsinfFlow() {
        launch {
            binding.searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { query ->
                    if (query.isEmpty()) {
                        runOnUiThread() {
                            //mAdapter.clear()
                            // mAdapter.notifyDataSetChanged()
                        }
                        return@filter true
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    dataFromNetwork(query)
                        .catch {
                            //emitAll(null)
                        }
                }
                .flowOn(Dispatchers.Main)
                .collect { result ->
                    result.let {
                        when (it) {
                            is Either.Right -> {
                                mAdapter.clear()
                                var values = it?.response?.query?.pages?.values;
                                var searchResults = ArrayList(values);
                                mAdapter.setData(searchResults)
                                mAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
        }
    }

    fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

        val query = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })

        return query

    }

    fun <T> List<T>.toArrayList(): ArrayList<T> {
        return ArrayList(this)
    }

    /**
     * Simulation of network data
     */
    private fun dataFromNetwork(query: String): Flow<Either<ErrorModel, SearchResult>> {
        return flow {
            //delay(2000)
            runOnUiThread {
                mAdapter.clear()
                mAdapter.notifyDataSetChanged()
            }
            if(!TextUtils.isEmpty(query)) {
                emit(viewModel.getSearchResult(query))
            }

        }
    }

}
package com.designwebtech.kambala.android.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahadream.wikiimagesearch.data.common.ErrorModel
import com.mahadream.wikiimagesearch.data.remote.connectivity.base.ConnectivityProvider
import com.mahadream.wikiimagesearch.utils.AppConstants
import com.mahadream.wikiimagesearch.utils.dialogutills.DialogUtills
import com.mahadream.wikiimagesearch.utils.dialogutills.IDialogListner

open abstract class BaseFragment : Fragment(), ConnectivityProvider.ConnectivityStateListener {
    private var isNetworkLoadCheckRequired: Boolean = false
    private var isLoading: Boolean? = false
    var isLoaded: Boolean = true
    var pageIndex = 1
    private lateinit var progressDialog: ProgressDialog
    protected var layoutManager: RecyclerView.LayoutManager? = null
    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(this.requireContext()) }


    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        val hasInternet = state.hasInternet()
        if (hasInternet && isNetworkLoadCheckRequired) {
            isNetworkLoadCheckRequired = false
            loadData()
            DialogUtills.removeDialog()
        } else if (!hasInternet) {
            isLoaded = false
            isNetworkLoadCheckRequired = true
        }
    }

    private fun ConnectivityProvider.NetworkState.hasInternet(): Boolean {
        return (this as? ConnectivityProvider.NetworkState.ConnectedState)?.hasInternet == true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    open abstract fun loadData();

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
        isNetworkLoadCheckRequired = false
        observeError()
    }

    protected open abstract fun observeError()

    protected open fun handleError(error: ErrorModel) {
        if (error != null) {
            when (error.errorCode) {
                AppConstants.INetworkError.NO_NETWORK -> {
                    activity?.runOnUiThread { DialogUtills.showNoNetworkDialog(this.requireContext()) }
                }
                else -> activity?.runOnUiThread {
                    DialogUtills.showApiError(this.requireContext(), object : IDialogListner {
                        override fun onPosetiveButtonClick() {
                            loadData()
                        }

                        override fun onNegativeButtonClick() {
                        }

                    })
                }
            }
        }
    }

    abstract fun initView();

    override fun onStart() {
        super.onStart()

        provider.addListener(this)
    }

    override fun onStop() {
        super.onStop()

        provider.removeListener(this)
    }

    protected val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int? = layoutManager?.getChildCount()
                val totalItemCount: Int? = layoutManager?.getItemCount()
                var firstVisibleItemPosition: Int? = 0
                if (layoutManager is LinearLayoutManager) {
                    firstVisibleItemPosition =
                        (layoutManager as LinearLayoutManager)?.findFirstVisibleItemPosition()
                } else if (layoutManager is GridLayoutManager) {
                    firstVisibleItemPosition =
                        (layoutManager as GridLayoutManager)?.findFirstVisibleItemPosition()
                }
                if (!isLoading!!) {
                    if (visibleItemCount != null && firstVisibleItemPosition != null) {
                        if (visibleItemCount + firstVisibleItemPosition >= 10 && firstVisibleItemPosition >= 0) {
                            loadPaginationData()
                            isLoading = true
                        }
                    }
                }
            }
        }

    public open fun loadPaginationData() {
        pageIndex++
    }

    open fun handleSearch(searchQuery : String?) {
        loadData()
    }
}
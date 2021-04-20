package com.mahadream.wikiimagesearch.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView?.root) {
    abstract fun <T> onBindData(data: T, position: Int)
    protected var mClickListener: ItemClickListner? = null
    var isNetworkAvailable = MutableLiveData<Boolean>()

    fun setListener(listener: ItemClickListner?) {
        mClickListener = listener
    }

    interface ItemClickListner {
        fun <T> onItemClick(data: T)
    }
}
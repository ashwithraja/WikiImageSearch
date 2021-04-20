package com.mahadream.wikiimagesearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahadream.wikiimagesearch.data.remote.Page
import com.mahadream.wikiimagesearch.ui.adapter.viewholder.SearchViewHolder
import com.mahadream.wikiimagesearch.ui.base.BaseViewHolder

class SearchAdapter : RecyclerView.Adapter<BaseViewHolder<Any>>() {
    var mData: ArrayList<Page> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return SearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                SearchViewHolder.getLayoutId(),
                parent,
                false
            )
        ) as BaseViewHolder<Any>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        if (holder is SearchViewHolder) {
            holder.onBindData(mData[position], position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(toArrayList: ArrayList<Page>) {
        mData.addAll(toArrayList)

    }

    fun clear() {
        mData.clear()
    }
}
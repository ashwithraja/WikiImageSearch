package com.mahadream.wikiimagesearch.ui.adapter.viewholder

import android.util.Log
import com.bumptech.glide.Glide
import com.mahadream.wikiimagesearch.R
import com.mahadream.wikiimagesearch.data.remote.Page
import com.mahadream.wikiimagesearch.databinding.SearchItemBinding
import com.mahadream.wikiimagesearch.ui.base.BaseViewHolder
import com.mahadream.wikiimagesearch.utils.DeviceUtills

class SearchViewHolder(var binding: SearchItemBinding) : BaseViewHolder<Any>(binding) {
    override fun <T> onBindData(data: T, position: Int) {
        /*  if (data is Page) {
              Log.d("search", data.title)
          }*/
        if (data is Page) {
            binding.executePendingBindings()
            data.thumbnail?.let { Log.i("Image", it?.source) }
            Glide.with(binding.imgView.context).load(data.thumbnail?.source)
                .placeholder(binding.imgView.context.getDrawable(R.drawable.ic_launcher_foreground))
                .into(binding.imgView)

            if (data.thumbnail != null) {
                var params = binding.imgView.layoutParams
                var width = DeviceUtills.convertPixelsToDp(
                    (((DeviceUtills.getDevideWidth() / 2).toLong())),
                    binding.imgView.context
                )
            }
        }
    }

    companion object {
        fun getLayoutId(): Int {
            return R.layout.search_item
        }
    }
}
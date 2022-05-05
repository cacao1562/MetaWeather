package com.example.metaweather.presentation.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.metaweather.presentation.ui.MainViewModel

object BindingAdapters {

//    @JvmStatic
//    @BindingAdapter("loadImageOrDefault")
//    fun <T: Any> loadImageOrDefault(imageView: ImageView, path: T?) {
//        if (path != null) {
//            val name = (path as String)
//            val url = "${Constants.BaseURL}/image/$name"
//            Glide.with(imageView)
//                .load(url)
//                .apply(
//                    RequestOptions()
//                        .placeholder(android.R.drawable.ic_menu_gallery)
//                        .error(android.R.drawable.stat_notify_error))
//                .into(imageView)
//        }else {
//            imageView.setImageResource(android.R.drawable.ic_menu_gallery)
//        }
//    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun isVisible(view: View, visible: Boolean?) {
        visible?.let {
            view.isVisible = it
        }
    }

    @JvmStatic
    @BindingAdapter("setSwipeRefresh")
    fun setSwipeRefresh(view: SwipeRefreshLayout, viewModel: MainViewModel) {
        view.setOnRefreshListener {
            viewModel.getLocationSearch()
        }
    }

    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun isRefreshing(view: SwipeRefreshLayout, isLoading: Boolean) {
        view.isRefreshing = isLoading
    }
}
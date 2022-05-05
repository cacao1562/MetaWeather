package com.example.metaweather.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.metaweather.R
import com.example.metaweather.domain.model.LocationInfo
import com.example.metaweather.presentation.base.BaseBindingViewHolder
import com.example.metaweather.presentation.utils.WeatherViewType

class WeatherAdapter: RecyclerView.Adapter<BaseBindingViewHolder<*, *>>() {

    private var mData = arrayListOf<LocationInfo>()

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> WeatherViewType.HEADER.ordinal
            else -> WeatherViewType.WEATHER.ordinal
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<*, *> {
        return when(WeatherViewType.values()[viewType]) {
            WeatherViewType.HEADER -> HeaderViewHolder(parent, R.layout.item_header)
            WeatherViewType.WEATHER -> WeatherViewHolder(parent, R.layout.item_weather)
        }
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<*, *>, position: Int) {
        (holder as BaseBindingViewHolder<Any, *>).bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(item: List<LocationInfo>) {
        mData.clear()
        mData.addAll(item)
        notifyDataSetChanged()
    }
}
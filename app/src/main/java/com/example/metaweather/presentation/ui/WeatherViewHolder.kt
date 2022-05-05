package com.example.metaweather.presentation.ui

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.metaweather.databinding.ItemWeatherBinding
import com.example.metaweather.domain.model.LocationInfo
import com.example.metaweather.presentation.base.BaseBindingViewHolder

class WeatherViewHolder constructor(
    parent: ViewGroup,
    @LayoutRes layoutRes: Int
): BaseBindingViewHolder<LocationInfo, ItemWeatherBinding>(parent, layoutRes) {

    override fun bind(obj: LocationInfo) {
        binding.data = obj
        binding.layoutToday.data = obj.locations[0]
        binding.layoutTomorrow.data = obj.locations[1]
    }
}
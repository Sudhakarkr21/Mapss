package com.example.mapss.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mapss.R
import com.example.mapss.other.TrackingUtility
import com.example.mapss.ui.viewmodels.MainViewModel
import com.example.mapss.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel : StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.runTotalDistance.observe(viewLifecycleOwner, Observer {
            tvTotalDistance.text = (it/1000f).toString() + "km"
        })

        viewModel.runTotalCaloriesBurned.observe(viewLifecycleOwner, Observer {
            tvTotalCalories.text = it.toString() + "kcal"
        })

        viewModel.runTotalAvgSpeed.observe(viewLifecycleOwner, Observer {
            tvAverageSpeed.text = it.toString() + "km/h"
        })

        viewModel.runTotalTimeInMillis.observe(viewLifecycleOwner, Observer {
            tvTotalTime.text = TrackingUtility.getFormattedStopWatchTime(it)
        })
    }
}
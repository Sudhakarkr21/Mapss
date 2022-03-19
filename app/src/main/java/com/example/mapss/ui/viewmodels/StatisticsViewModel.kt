package com.example.mapss.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mapss.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val runTotalAvgSpeed = mainRepository.getTotalAvgSpeed()

    val runTotalDistance = mainRepository.getTotalDistance()

    val runTotalCaloriesBurned = mainRepository.getTotalCaloriesBurned()

    val runTotalTimeInMillis = mainRepository.getTotalTimeInMillis()
}
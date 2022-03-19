package com.example.mapss.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapss.db.Run
import com.example.mapss.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {


    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()

    val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()

    val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

    val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCaloriesBurned()

    val runsSortedByTimeInMillis = mainRepository.getAllRunsSortedByTimeInMillis()

    fun insert(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    suspend fun delete(run: Run) = withContext(viewModelScope.coroutineContext) {
        var isdelete = mainRepository.deleteRun(run)
    }
}
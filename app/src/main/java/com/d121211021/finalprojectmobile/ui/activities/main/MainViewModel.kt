package com.d121211021.finalprojectmobile.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211021.finalprojectmobile.MyApplication
import com.d121211021.finalprojectmobile.data.models.Place
import com.d121211021.finalprojectmobile.data.repository.PlaceRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class  Success(val place: List<Place>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val placeRepository: PlaceRepository): ViewModel() {

    //    initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getPlace() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = placeRepository.getPlace()
            mainUiState = MainUiState.Success(result.hits.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getPlace()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val placeRepository = application.container.placeRepository
                MainViewModel(placeRepository)
            }
        }
    }
}
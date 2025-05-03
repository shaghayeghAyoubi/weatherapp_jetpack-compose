package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

//    private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
//    val weatherState: StateFlow<WeatherResponse?> = _weatherState
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: StateFlow<String?> = _errorMessage

    private val _uiState = MutableStateFlow<UiState<WeatherResponse>>(UiState.Loading)
    val uiState : StateFlow<UiState<WeatherResponse>> = _uiState

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val weather = getWeatherUseCase(city)
                _uiState.value = UiState.Success(weather)
            }catch (e: IOException) {
                _uiState.value = UiState.Error("Check your internet connection.")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Server error: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Unexpected error: ${e.localizedMessage}")
            }
//            finally {
//                _isLoading.value = false
//            }
        }
    }
}

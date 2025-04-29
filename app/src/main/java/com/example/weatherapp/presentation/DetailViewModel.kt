package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.ForecastResponse
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getForecastUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastResponse?>(null)
    val forecastState: StateFlow<ForecastResponse?> = _forecastState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchForecast(city: String, days: Int = 3) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val forecast = getForecastUseCase.invoke(city, days)
                _forecastState.value = forecast
            } catch (e: IOException) {
                _errorMessage.value = "Check your internet connection."
                _forecastState.value = null
            } catch (e: HttpException) {
                _errorMessage.value = "Server error: ${e.message()}"
                _forecastState.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Unexpected error: ${e.localizedMessage}"
                _forecastState.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

package com.kst.testing.code_challenge_android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kst.testing.code_challenge_android.network.UIState
import com.kst.testing.code_challenge_android.repository.PropertiesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository,
    @Named("IO")
    private val dispatcher: CoroutineDispatcher


) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Error(null))
    val uiState: StateFlow<UIState> = _uiState

    fun getNewData() =
        viewModelScope.launch {
            propertiesRepository.getPropertiesFlow()
                .flowOn(dispatcher)
                .catch { exception ->
                    _uiState.value = UIState.Error(exception)
                }
                .collect { properties ->
                    if (properties.propertyList == null) {
                        _uiState.value = UIState.Error()

                    } else {
                        val mapResult: MutableList<Int> = properties.propertyList.stream()
                            .filter { it.price != null && it.price > 0 }
                            .map { it.price }
                            .collect(Collectors.toList())

                        if (mapResult.size > 0) {
                            val average = mapResult.toIntArray().average()
                            _uiState.value = UIState.Success(average.toInt())
                        } else {
                            _uiState.value = UIState.Error()
                        }
                    }
                }
        }
}
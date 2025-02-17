package com.anhndt.quote.ui.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anhndt.quote.domain.usecase.QuoteUseCase
import com.anhndt.quote.ui.state.QuoteListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val quoteUseCase: QuoteUseCase
) : ViewModel(), DefaultLifecycleObserver {
    private val _uiState = MutableStateFlow<QuoteListState>(QuoteListState.Loading)
    val uiState: StateFlow<QuoteListState> = _uiState
    private var getQuoteListJob: Job? = null

    fun getQuoteList(isForceRefresh: Boolean = false) {
        _uiState.value = QuoteListState.Loading

        getQuoteListJob = viewModelScope.launch(Dispatchers.IO) {
            quoteUseCase.getQuotes(isForceRefresh = isForceRefresh).onSuccess {
                _uiState.value = QuoteListState.Success(it)
            }.onFailure {
                _uiState.value = QuoteListState.Error(it.message ?: "Unknown error")
            }
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        getQuoteListJob?.cancel()
        super.onPause(owner)
    }
}
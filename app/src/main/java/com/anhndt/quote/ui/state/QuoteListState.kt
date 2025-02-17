package com.anhndt.quote.ui.state

import com.anhndt.quote.domain.model.Quote

sealed class QuoteListState {
    data object Loading : QuoteListState()
    data class Success(val quotes: List<Quote>?) : QuoteListState()
    data class Error(val message: String) : QuoteListState()
}